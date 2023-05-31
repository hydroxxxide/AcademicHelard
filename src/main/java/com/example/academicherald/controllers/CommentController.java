package com.example.academicherald.controllers;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.mappers.CommentMapper;
import com.example.academicherald.entity.Comment;
import com.example.academicherald.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/create")
    public CommentDto create(@RequestBody Comment comment,
                             @RequestParam Long userId,
                             @RequestParam Long publicationId) {
        return commentMapper.convertToDto(commentService.create(comment, userId, publicationId));
    }

    @GetMapping("/getById/{id}")
    public CommentDto getById(@PathVariable Long id) {
        return commentMapper.convertToDto(commentService.getCommentById(id));
    }

    @GetMapping("/getByPublication/{publicationId}")
    public List<CommentDto> getByPublicationId(@PathVariable Long publicationId) {
        return commentMapper.convertToDTOList(commentService.allCommentsByIdPublic(publicationId));
    }

    @GetMapping("/getByUser/{userId}")
    public List<CommentDto> getCommentByUserId(@PathVariable Long userId) {
        return commentMapper.convertToDTOList(commentService.allCommentsByUser(userId));
    }

    @PutMapping("/update")
    public CommentDto update(@RequestBody Comment comment) {
        return commentMapper.convertToDto(commentService.updateComment(comment));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
