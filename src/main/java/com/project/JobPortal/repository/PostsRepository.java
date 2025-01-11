package com.project.JobPortal.repository;

import com.project.JobPortal.model.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends MongoRepository<Posts,String> {

    Optional<Posts> findByProfile(String profile);


    @Query("{$or: [{'profile': ?0}, {'city.name': ?0}, {'techs': {$in: [?0]}}]}")
    List<Posts> searchProperty(String searchParam);


}
