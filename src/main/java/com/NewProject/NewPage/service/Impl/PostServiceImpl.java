package com.NewProject.NewPage.service.Impl;

import com.NewProject.NewPage.entity.Post;
import com.NewProject.NewPage.exception.ResouceNotFoundException;
import com.NewProject.NewPage.payload.PostDto;
import com.NewProject.NewPage.repository.PostRepository;
import com.NewProject.NewPage.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {



//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        Post post = mapToEntity(postDto);

        Post savedPost= postRepository.save(post);

//        PostDto dto = new PostDto(); // Return PostDto
//        dto.setTitle(savedPost.getTitle());
//        dto.setDescription(savedPost.getDescription());
//        dto.setContent(savedPost.getContent());

        PostDto dto = mapToDto(savedPost);

        return dto;
    }

    @Override
    public PostDto getPostById(long id) {

        Post post = postRepository.findById(id).orElseThrow( //supplier
                ()->new ResouceNotFoundException("Post Not found with id:-"+id)
        );
        // Convert Post Object to dto

        PostDto dto = new PostDto(); // Return PostDto
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    // this is using for GetAll Post
//    @Override
//    public List<PostDto> getAllPost() {
//
//        List<Post> posts = postRepository.findAll();
//        List<PostDto> dtos = posts.stream().map(n-> mapToDto(n)).collect(Collectors.toList());
//        return dtos;
//    }
//
//    PostDto mapToDto(Post post)  // return PostDto
//    {
//        PostDto dto = new PostDto(); // Return PostDto
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
//        return  dto;
//    }
//
//    Post mapToEntity(PostDto postDto)  // return PostDto
//    {
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//        return post;
//    }


    // this is using for pagination
    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

//      Pageable pageable = PageRequest.of(pageNo,pageSize);
       Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> posts = postPage.getContent(); // convert pagePost to ListPost then we use getContent

        List<PostDto> dtos = posts.stream().map(news -> mapToDto(news)).collect(Collectors.toList());
        return dtos;
    }

    PostDto mapToDto(Post post)  // return PostDto
    {
        PostDto dto = new PostDto(); // Return PostDto
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return  dto;
    }

    Post mapToEntity(PostDto postDto)  // return PostDto
    {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}








// crud Repository not help you to do pagination
// pagination concept in crud repository is not there
// if you want to apply pagination and sorting then use JPA Repository