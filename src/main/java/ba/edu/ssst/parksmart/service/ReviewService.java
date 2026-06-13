package ba.edu.ssst.parksmart.service;

import ba.edu.ssst.parksmart.model.Review;
import ba.edu.ssst.parksmart.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviewsByParkingId(Long parkingId) {
        return reviewRepository.findByParkingId(parkingId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Review addReview(Long userId, Long parkingId, Integer rating, String comment) {
        return reviewRepository.save(
                Review.builder()
                        .userId(userId)
                        .parkingId(parkingId)
                        .rating(rating)
                        .comment(comment)
                        .build()
        );
    }

    public Double getAverageRating(Long parkingId) {
        List<Review> reviews = reviewRepository.findByParkingId(parkingId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public boolean hasUserReviewed(Long userId, Long parkingId) {
        return reviewRepository.findByUserIdAndParkingId(userId, parkingId).isPresent();
    }
}
