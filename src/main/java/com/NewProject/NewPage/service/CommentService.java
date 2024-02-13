package com.NewProject.NewPage.service;

import com.NewProject.NewPage.payload.CommentDto;


public interface CommentService {

    CommentDto createComment(CommentDto commentDto, long postId);

    // delete by id
    void deleteComment(long id);

    // update by id
    CommentDto updateComment(long id, CommentDto commentDto);
}
