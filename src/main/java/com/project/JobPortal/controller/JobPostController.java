package com.project.JobPortal.controller;
import com.project.JobPortal.payrole.PostDto;
import com.project.JobPortal.service.PostService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class JobPostController {

    @Autowired
    private PostService postService;
    @Hidden
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws  IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody PostDto dto,@RequestParam String cityName){
        ResponseEntity<?> response = postService.addPosts(dto,cityName);
        return response;
    }

    @GetMapping("/get/allPost")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/postByInput")
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam String searchParam){
        return new ResponseEntity<>(postService.getPost(searchParam),HttpStatus.OK);
    }
}
