package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.ChapterDto;
import com.example.academicherald.dto.lmsDto.CourseDto;
import com.example.academicherald.mappers.lmsMapper.ChapterMapper;
import com.example.academicherald.models.lms.Chapter;
import com.example.academicherald.models.lms.Course;
import com.example.academicherald.services.lmsService.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    public ChapterController(ChapterService chapterService, ChapterMapper chapterMapper) {
        this.chapterService = chapterService;
        this.chapterMapper = chapterMapper;
    }

    @PostMapping("/create")
    public ChapterDto createChapter(@RequestBody ChapterDto chapterDto,
                                    @RequestParam Long courseId) {
        Chapter chapter = chapterMapper.convertToEntity(chapterDto);
        Chapter createdChapter = chapterService.createChapter(chapter, courseId);
        return chapterMapper.convertToDto(createdChapter);
    }
    @PostMapping("/addStudents/{chapterId}")
    public ResponseEntity<?> addLectureToChapter(@PathVariable Long chapterId,
                                                 @RequestBody List<Long> lectureIds) {
        try {
            Chapter updatedChapter= chapterService.addLectures(chapterId, lectureIds);
            ChapterDto chapterDto = chapterMapper.convertToDto(updatedChapter);
            return ResponseEntity.ok(chapterDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
