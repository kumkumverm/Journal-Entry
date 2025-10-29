package net.engineeringdigest.journalApp.entity;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@Document(collection = "journalEntries")
public class JournalEntry {
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
