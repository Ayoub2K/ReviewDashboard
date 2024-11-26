package com.example.ReviewDashboard.controller;

import com.example.ReviewDashboard.domain.Review;
import com.example.ReviewDashboard.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {this.reviewService = reviewService;}

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        long jsonId = Long.parseLong(String.valueOf(review.getPerformanceId()));
        review.setPerformanceId(jsonId);
        reviewService.saveReview(review);
        return ResponseEntity.ok(review).getBody();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getDjById(@PathVariable long id) {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/performanceId/{id}")
    public List<Review> getAllReviewsWithPerformanceId(@PathVariable long id) {
        return reviewService.getAllReviewsWithPerformanceId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id, @RequestBody Review updatedReview) throws Exception {
        long jsonId = Long.parseLong(String.valueOf(updatedReview.getPerformanceId()));
        updatedReview.setPerformanceId(jsonId);
        Review review = reviewService.updateReview(id, updatedReview);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) throws Exception {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
