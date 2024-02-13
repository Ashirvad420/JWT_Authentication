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

    //DeleteCommnet

    // http://localhost:8080/api/comments/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id)
    {
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment is deleted!",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,@RequestBody CommentDto commentDto)
    {
        CommentDto dto = commentService.updateComment(id, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}






// Date 2024-01-24 to 2024-01-24 to 2024-01-29 to 2024-01-30

/*
the question mark (?) is used to indicate the start of the query parameters.
Query parameters are used to pass data to the server as part of an HTTP request.
 */