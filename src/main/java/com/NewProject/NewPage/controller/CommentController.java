package com.NewProject.NewPage.controller;

import com.NewProject.NewPage.payload.CommentDto;
import com.NewProject.NewPage.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    private CommentService commentService;


    //http://localhost:8080/api/comments?postId=1
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @RequestParam long postId){
        CommentDto dto = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    //http://localhost:8080/api/comments/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        commentService.deleteComment( id);
        return new ResponseEntity<>("Your comment is deleted", HttpStatus.OK);

    }
    //http:localhost:8080/api/comments/{id}/post/{postId}
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,@RequestBody CommentDto commentDto,@PathVariable long postId){
        CommentDto dto = commentService.updateComment(id,commentDto,postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
}





// Date 2024-01-24 to 2024-01-24 to 2024-01-29 to 2024-01-30

/*
the question mark (?) is used to indicate the start of the query parameters.
Query parameters are used to pass data to the server as part of an HTTP request.
 */