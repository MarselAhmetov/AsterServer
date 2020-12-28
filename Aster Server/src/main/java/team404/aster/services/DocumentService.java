package team404.aster.services;

import com.itextpdf.text.Document;
import team404.aster.domain.dto.DocumentDto;

public interface DocumentService {
    Document generateDocument(String type, DocumentDto documentDto, Document document);
}
