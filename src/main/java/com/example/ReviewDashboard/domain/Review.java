package com.example.ReviewDashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;

@Getter
@Entity
@Data
@Table(name = "reviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Review implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String Name;
    private String review;
    private Long PerformanceId;

    public Review() {

    }

    public Review(String name, String review, Long performanceId) {
        this.Name = name;
        this.review = review;
        this.PerformanceId = performanceId;
    }
}
