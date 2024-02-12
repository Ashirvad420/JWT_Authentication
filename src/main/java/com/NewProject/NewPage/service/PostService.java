package com.NewProject.NewPage.service;

import com.NewProject.NewPage.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {


    PostDto createPost(PostDto postDto);

    // this is using for GetAll Id
    PostDto getPostById(long id);

    // this is for pagination
    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    // this is using for GetAll Post
//    List<PostDto> getAllPost();

}
