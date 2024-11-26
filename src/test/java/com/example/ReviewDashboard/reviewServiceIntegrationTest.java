package com.example.ReviewDashboard;

import com.example.ReviewDashboard.domain.Review;
import com.example.ReviewDashboard.exception.ReviewNotFoundException;
import com.example.ReviewDashboard.repository.ReviewRepository;
import com.example.ReviewDashboard.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class reviewServiceIntegrationTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    private Review sampleReview;
    private Review updatedReview;

    @BeforeEach
    public void setUp() {
        sampleReview = new Review();
        sampleReview.setId(1L);
        sampleReview.setName("anonieme gast");
        sampleReview.setPerformanceId(1L);
        sampleReview.setReview("best performance of my life!");

        updatedReview = new Review();
        updatedReview.setId(1L);
        updatedReview.setName("anonieme gast updated");
        updatedReview.setPerformanceId(1L);
        updatedReview.setReview("worst performance of my life!");
    }

    @Test
    public void testSaveReview() {
        when(reviewRepository.save(sampleReview)).thenReturn(sampleReview);

        Review savedReview = reviewService.saveReview(sampleReview);

        assertNotNull(savedReview);
        assertEquals("anonieme gast", savedReview.getName());
        verify(reviewRepository, times(1)).save(sampleReview);
    }

    @Test
    public void testGetReviewById() {
        when(reviewRepository.findReviewById(1L)).thenReturn(Optional.of(sampleReview));

        Review foundReview = reviewService.getReviewById(1L);

        assertNotNull(foundReview);
        assertEquals(1L, foundReview.getId());
        assertEquals("anonieme gast", foundReview.getName());
    }

    @Test
    public void testGetReviewById_NotFound() {
        when(reviewRepository.findReviewById(1L)).thenReturn(Optional.empty());

        assertThrows(ReviewNotFoundException.class, () -> reviewService.getReviewById(1L));
    }

    @Test
    public void testUpdateReview() {
        when(reviewRepository.findReviewById(1L)).thenReturn(Optional.of(sampleReview));
        when(reviewRepository.save(sampleReview)).thenReturn(updatedReview);

        Review updated = reviewService.updateReview(1L, updatedReview);

        assertNotNull(updated);
        assertEquals("anonieme gast updated", updated.getName());
        verify(reviewRepository, times(1)).save(sampleReview);
        assertEquals(1L, sampleReview.getPerformanceId()); // Ensure sampleReview has been updated
    }

    @Test
    public void testDeleteReview() throws Exception {
        when(reviewRepository.findReviewById(1L)).thenReturn(Optional.of(sampleReview));

        reviewService.deleteReview(1L);

        verify(reviewRepository, times(1)).delete(sampleReview);
    }

    @Test
    public void testGetAllReviews() {
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(sampleReview));

        List<Review> reviews = reviewService.getAllReviews();

        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());
        assertEquals(1, reviews.size());
        verify(reviewRepository, times(1)).findAll();
    }
}
