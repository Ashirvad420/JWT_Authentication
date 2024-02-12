package com.NewProject.NewPage.controller;

import com.NewProject.NewPage.payload.CommentDto;
import com.NewProject.NewPage.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

            // call service layer
        private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // http://localhost:8080/api/comments?postId=1  // post Id as query parameter
    @PostMapping
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @RequestParam long postId               // how to a id read query parameter
            ) // comment as a JSON Object
    {
       CommentDto dto = commentService.createComment(commentDto,postId);
       return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}

// Date 2024-01-24 to 2024-01-24 to 2024-01-29