package com.NewProject.NewPage.service;

import com.NewProject.NewPage.payload.CommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {


    CommentDto createComment( CommentDto commentDto,  long postId);

    void deleteComment(long id);

    CommentDto updateComment(long id, CommentDto commentDto, long postId);
}
