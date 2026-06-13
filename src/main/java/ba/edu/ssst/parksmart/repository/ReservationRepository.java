package ba.edu.ssst.parksmart.repository;

import ba.edu.ssst.parksmart.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByParkingId(Long parkingId);

    List<Reservation> findByUserIdAndStatus(Long userId, String status);
}
