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

    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;

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
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
       Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResouceNotFoundException("Post  not found id: " + id) // this for postId
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Comment not found id: " + id)
        );
        // Return Back the Comment

        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment savedComment = commentRepository.save(c);
        return mapToDto(savedComment);
    }

    // create method for modelMapper
    CommentDto mapToDto(Comment comment) {
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

}