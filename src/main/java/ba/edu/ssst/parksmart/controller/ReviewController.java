package ba.edu.ssst.parksmart.controller;

import ba.edu.ssst.parksmart.model.Review;
import ba.edu.ssst.parksmart.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = {"http://localhost:4200", "https://parksmart-sarajevo.netlify.app"})
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/parking/{parkingId}")
    public ResponseEntity<List<Review>> getReviewsByParkingId(@PathVariable Long parkingId) {
        return ResponseEntity.ok(reviewService.getReviewsByParkingId(parkingId));
    }

    @GetMapping("/parking/{parkingId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long parkingId) {
        return ResponseEntity.ok(reviewService.getAverageRating(parkingId));
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Map<String, Object> body) {
        Long userId = Long.valueOf(body.get("userId").toString());
        Long parkingId = Long.valueOf(body.get("parkingId").toString());
        Integer rating = Integer.valueOf(body.get("rating").toString());
        String comment = (String) body.get("comment");
        return ResponseEntity.ok(reviewService.addReview(userId, parkingId, rating, comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
    }

    @GetMapping("/parking/{parkingId}/user/{userId}")
    public ResponseEntity<Boolean> hasUserReviewed(@PathVariable Long parkingId, @PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.hasUserReviewed(userId, parkingId));
    }
}
