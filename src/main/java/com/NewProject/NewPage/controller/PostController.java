package com.NewProject.NewPage.controller;

import com.NewProject.NewPage.payload.PostDto;
import com.NewProject.NewPage.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // this is for get data in database
    @PostMapping
    public ResponseEntity<PostDto> createDto(@RequestBody PostDto postDto)
    {
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // find the data using id
    // http://localhost:8080/api/posts/all?id=1
    @GetMapping("/all")
    public  ResponseEntity<PostDto>  getPostById(@RequestParam long id)
    {
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    // see all the data
//    @GetMapping
//    public List<PostDto> getAllPost()
//    {
//
//             List<PostDto> postDtos=  postService.getAllPost();
//             return postDtos;
//    }


    // this is using for pagination

    // http://localhsot:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=desc

    @GetMapping
    public  List<PostDto> getAllPost(
            @RequestParam(name = "pageNo",required = false,defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize",required = false,defaultValue = "4") int pageSize,
            @RequestParam(name = "sortBy",required = false,defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false,defaultValue = "id") String sortDir
    )
    {
        List<PostDto> postDtos = postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
        return postDtos;
    }
}

// if we don't give required pageNo then print defaultValue zero("0")
// day 2024-01-17



// i have to read the data from pastman using pageNo=0&pageSize=3
// even i dont give required number then return dafualt value zero
// in default value number given anything for PageSize
// PageNo and PageSize supply to GetAllPost Method