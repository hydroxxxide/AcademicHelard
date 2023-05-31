package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.enums.PublicationType;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.entity.Publication;
import com.example.academicherald.repositories.UserRepository;
import com.example.academicherald.services.EmailService;
import com.example.academicherald.services.PublicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;
    private final PublicationMapper mapper;
    private final EmailService emailService;
    private final UserRepository userRepository;
    ;

    public PublicationController(PublicationService publicationService, PublicationMapper mapper, EmailService emailService, UserRepository userRepository) {
        this.publicationService = publicationService;
        this.mapper = mapper;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }


    @PostMapping("/create")
    public PublicationDto createPublication(
            @RequestBody PublicationDto publicationDto,
            @RequestParam Long userId,
            @RequestParam Long categoryId
    ) {
        Publication publication = mapper.convertToEntity(publicationDto);
        Publication createdPublication = publicationService.create(publication, userId, categoryId);
       if (createdPublication.getType().equals(PublicationType.NEWS)){
           for (String email:
                   userRepository.findAllEmails()) {
               String subject = "Новая новость";
               String text = "Добрый день, у нас есть новая новость!\n\n" +
                       publication.getTitle() +
                       "\n" +
                       publication.getSubtitle();
               emailService.sendSimpleMessage(email, subject, text);
           }
       }
        return mapper.convertToDto(createdPublication);
    }

    @GetMapping("/get/{id}")
    public PublicationDto getById(@PathVariable Long id) {
        return mapper.convertToDto(publicationService.getById(id));
    }

    @GetMapping("/get/all")
    public List<PublicationDto> getAll() {
        return mapper.convertToDTOList(publicationService.getAllAccepted());
    }

    @PutMapping("/update/{userId}")
    public PublicationDto update(@RequestBody Publication publication, @PathVariable Long userId) throws Exception {
        return mapper.convertToDto(publicationService.update(publication, userId));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        publicationService.delete(id);
    }

}
