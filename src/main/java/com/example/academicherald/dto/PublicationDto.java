package com.example.academicherald.dto;

import com.example.academicherald.enums.PublicationType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicationDto {
    private String title;
    private String subtitle;
    private String text;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation;
    private CategoryDto category;
    private List<TagDto> tags;
    private UserDto author;
    @Enumerated(EnumType.STRING)
    private PublicationType type;
}
