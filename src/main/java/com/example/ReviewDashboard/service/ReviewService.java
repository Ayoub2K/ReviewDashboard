package com.example.ReviewDashboard.service;

import com.example.ReviewDashboard.domain.Review;
import com.example.ReviewDashboard.exception.ReviewNotFoundException;
import com.example.ReviewDashboard.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository) {this.reviewRepository = reviewRepository;}

    public Review getReviewById(Long id) {
        return reviewRepository.findReviewById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with id " + id + " not found"));
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review updateReview(long id, Review updatedReview) {
        Review existingReview = reviewRepository.findReviewById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with id " + id + " not found"));;
        existingReview.setName(updatedReview.getName());
        existingReview.setReview(updatedReview.getReview());
        existingReview.setPerformanceId(updatedReview.getPerformanceId());
        return reviewRepository.save(existingReview);
    }

    public void deleteReview(long id) throws Exception {
        Review existingReview = reviewRepository.findReviewById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with id " + id + " not found"));;
        reviewRepository.delete(existingReview);
    }

    public List<Review> getAllReviewsWithPerformanceId(long id) {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream()
                .filter(review -> review.getPerformanceId() == id)
                .collect(Collectors.toList());
    }

}
