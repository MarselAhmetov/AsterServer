package team404.aster;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.rabbitmq.client.*;
import team404.aster.domain.dto.DocumentDto;
import team404.aster.services.DocumentServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

// приложение, которое отправляет сообщения в очереди
public class Consumer {

    private final static String EXCHANGE_NAME = "documents";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String TYPE = "offer";


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");

            ObjectMapper objectMapper = new ObjectMapper();
            DocumentServiceImpl documentService = new DocumentServiceImpl();

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                DocumentDto documentDto = objectMapper.readValue(message.getBody(), DocumentDto.class);
                try {

                    File file = new File("documents/" + documentDto.getLastName() + "_" + TYPE + ".pdf");
                    file.createNewFile();
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(file));
                    document.open();
                    documentService.generateDocument(TYPE, documentDto, document);
                    document.close();
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);

                } catch (DocumentException e) {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                    System.err.println("Error");
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

