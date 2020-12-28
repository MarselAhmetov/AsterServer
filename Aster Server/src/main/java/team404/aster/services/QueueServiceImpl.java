package team404.aster.services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Override
    public void sendToQueue(String queue, String message) {
        try {
            sendToQueue0(queue, message);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendToQueueExchange(String exchangeName, String exchangeType, String message) {
        try {
            sendToQueueExchange0(exchangeName, exchangeType, message);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendListToQueueExchange(String exchangeName, String exchangeType, List<String> messages) {
        try {
            sendListToQueueExchange0(exchangeName, exchangeType, messages);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void sendToQueue0(String queue, String message) throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish("", queue, null, message.getBytes());
        connection.close();
    }

    private void sendToQueueExchange0(String exchangeName, String exchangeType, String message) throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, exchangeType);
        channel.basicPublish(exchangeName, "", null, message.getBytes());
        connection.close();
    }

    private void sendListToQueueExchange0(String exchangeName, String exchangeType, List<String> messages) throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, exchangeType);
        for (String message : messages) {
            channel.basicPublish(exchangeName, "", null, message.getBytes());
        }
        connection.close();
    }
}
