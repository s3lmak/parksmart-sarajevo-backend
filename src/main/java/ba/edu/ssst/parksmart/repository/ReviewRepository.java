package ba.edu.ssst.parksmart.repository;

import ba.edu.ssst.parksmart.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByParkingId(Long parkingId);

    List<Review> findByUserId(Long userId);

    Optional<Review> findByUserIdAndParkingId(Long userId, Long parkingId);
}
