package team404.aster.services;

import com.itextpdf.text.*;
import org.springframework.stereotype.Service;
import team404.aster.domain.dto.DocumentDto;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Override
    public Document generateDocument(String type, DocumentDto documentDto, Document document) {
        try {
            document.open();

            Paragraph p = new Paragraph();
            p.add(type);
            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            Paragraph p2 = new Paragraph();
            p2.add(
                    "Name: " + documentDto.getFirstName() + "\n" +
                    "LastName: " + documentDto.getLastName() + "\n" +
                    "Age: " + documentDto.getAge());//no alignment

            document.add(p2);

            document.close();
            return document;
        } catch (DocumentException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
