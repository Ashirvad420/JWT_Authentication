package com.NewProject.NewPage.service.Impl;


import com.NewProject.NewPage.entity.Comment;
import com.NewProject.NewPage.entity.Post;
import com.NewProject.NewPage.exception.ResouceNotFoundException;
import com.NewProject.NewPage.payload.CommentDto;
import com.NewProject.NewPage.repository.CommentRepository;
import com.NewProject.NewPage.repository.PostRepository;
import com.NewProject.NewPage.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;


    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;

    }

    // this is for CreateComment
    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResouceNotFoundException("Post Not found with id:" + postId)
        );

        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(savedComment.getId());
        dto.setEmail(savedComment.getEmail());
        dto.setText(savedComment.getText());


        return dto;
    }

    // this is for delete Comment
    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    // this is for update Comment
    @Override
    public CommentDto updateComment(long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new ResouceNotFoundException("Comment not found id: "+id)
        );
        return commentDto;
    }

}