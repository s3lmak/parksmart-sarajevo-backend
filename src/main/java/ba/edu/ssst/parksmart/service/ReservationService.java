package ba.edu.ssst.parksmart.service;

import ba.edu.ssst.parksmart.model.Reservation;
import ba.edu.ssst.parksmart.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Long userId, Long parkingId, LocalDateTime startTime, LocalDateTime endTime) {
        return reservationRepository.save(
                Reservation.builder()
                        .userId(userId)
                        .parkingId(parkingId)
                        .startTime(startTime)
                        .endTime(endTime)
                        .status("active")
                        .build()
        );
    }

    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Reservation cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));
        reservation.setStatus("cancelled");
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getActiveReservations(Long userId) {
        return reservationRepository.findByUserIdAndStatus(userId, "active");
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
