package com.project.JobPortal.repository;

import com.project.JobPortal.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CityRepository extends MongoRepository<City,String> {
    Optional<City> findByCityName(String cityName);

}
