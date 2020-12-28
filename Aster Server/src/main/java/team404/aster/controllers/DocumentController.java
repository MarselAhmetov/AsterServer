package team404.aster.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.aster.domain.dto.DocumentDto;
import team404.aster.services.QueueService;

@RestController
public class DocumentController {

    @Autowired
    private QueueService queueService;

    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/prepareDocuments")
    public ResponseEntity<String> prepareDocuments(@RequestBody DocumentDto document) {
        String message;
        try {
            message = objectMapper.writeValueAsString(document);
            queueService.sendToQueueExchange("documents", "fanout", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Ok");
    }

}
