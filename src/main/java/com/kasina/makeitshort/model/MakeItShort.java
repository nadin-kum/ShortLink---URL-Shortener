package com.kasina.makeitshort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasina.makeitshort.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@Document(collection = "urls")
public class MakeItShort {
    @Id
    private String urlId;

    @Lob
    private String originalUrl;

    @Indexed(unique = true)
    private String shortLink;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime creationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime expirationDate;
    @JsonIgnore
    @DBRef
    private User user;


    public MakeItShort() {
        // Generate a UUID as the URL ID
        this.urlId = UUID.randomUUID().toString();
    }
}

