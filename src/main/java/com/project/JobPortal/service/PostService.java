package com.project.JobPortal.service;

import com.project.JobPortal.model.City;
import com.project.JobPortal.model.Posts;
import com.project.JobPortal.payrole.PostDto;
import com.project.JobPortal.repository.CityRepository;
import com.project.JobPortal.repository.PostsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CityRepository cityRepository;

    private Posts mapToEntity(PostDto dto){
        return modelMapper.map(dto,Posts.class);
    }

    private PostDto mapToDto(Posts posts){
        return modelMapper.map(posts,PostDto.class);
    }

    public ResponseEntity<?> addPosts(PostDto dto, String cityName) {
        Posts post = mapToEntity(dto);
        post.setId(UUID.randomUUID().toString());
        Optional<Posts> opProfile = postsRepository.findByProfile(post.getProfile());
        if(opProfile.isPresent()){
            return new ResponseEntity<>("User profile is already present", HttpStatus.BAD_REQUEST);
        }
        City city = cityRepository.findByCityName(cityName).orElseThrow(()->new RuntimeException("Could not find city"));
        post.setCity(city);
        Posts savedPost =  postsRepository.save(post);
        return new ResponseEntity<>(mapToDto(savedPost),HttpStatus.CREATED);
    }

    public List<PostDto> getAllPosts() {
        List<Posts> allPosts = postsRepository.findAll();
        return allPosts.stream().map(e->mapToDto(e)).collect(Collectors.toList());
    }

    public List<PostDto> getPost(String searchParam){
        List<Posts> getAllPosts = postsRepository.searchProperty(searchParam);
        List<PostDto> postDtos = getAllPosts.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return postDtos;
    }
}
