package com.example.academicherald.dto;

import com.example.academicherald.enums.PublicationType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
public class PublicationDto {
    private String title;
    private String subtitle;
    private String text;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation;
    private CategoryDto category;
    private UserDto author;
    @Enumerated(EnumType.STRING)
    private PublicationType type;
}
