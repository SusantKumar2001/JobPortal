package com.project.JobPortal.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "city")
public class City {
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    private String cityName;

}
