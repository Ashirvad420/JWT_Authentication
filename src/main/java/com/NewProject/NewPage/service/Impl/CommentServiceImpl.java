package com.NewProject.NewPage.service.Impl;


import com.NewProject.NewPage.entity.Comment;
import com.NewProject.NewPage.entity.Post;
import com.NewProject.NewPage.exception.ResourceNotFoundException;
import com.NewProject.NewPage.payload.CommentDto;
import com.NewProject.NewPage.repository.CommentRepository;
import com.NewProject.NewPage.repository.PostRepository;
import com.NewProject.NewPage.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Autowired
    private PostRepository postRepository;

    private CommentRepository commentRepository;
    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post not found with id: " + postId));

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

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found: " + postId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment not found for id: " + id) );
        comment.setText(commentDto.getText());
        comment.setEmail(commentDto.getEmail());
        comment.setPost(post);

        Comment saved = commentRepository.save(comment);

        return mapToDto(saved);
    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}