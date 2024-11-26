package com.example.ReviewDashboard;

import com.example.ReviewDashboard.domain.Review;
import com.example.ReviewDashboard.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ReviewDashboardApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ReviewDashboardApplication.class);

	private final ReviewRepository reviewRepository;

	public ReviewDashboardApplication(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReviewDashboardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (reviewRepository.count() == 0) {
			Review review1 = reviewRepository.save(new Review("Festival goer", "best performance of my life!", 2L));
			Review review2 = reviewRepository.save(new Review("Hiphop Fan", "I love Hiphop!", 1L));
			Review review3 = reviewRepository.save(new Review("clasical Fan", "Worst performance of my life!", 2L));
		} else {
			logger.info("reviews already exist in the database.");
		}
	}
}
