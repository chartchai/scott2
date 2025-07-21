package camt.scott2.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "mails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    @Id
    private String id;
    private String from;
    private List<String> recipients;
    private String subject;
    private Map<String, Object> content;
    private String sendDate;
}