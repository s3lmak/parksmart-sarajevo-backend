package ba.edu.ssst.parksmart.controller;

import ba.edu.ssst.parksmart.model.Reservation;
import ba.edu.ssst.parksmart.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = {"http://localhost:4200", "https://parksmart-sarajevo.netlify.app"})
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Map<String, String> body) {
        Long userId = Long.parseLong(body.get("userId"));
        Long parkingId = Long.parseLong(body.get("parkingId"));
        LocalDateTime startTime = LocalDateTime.parse(body.get("startTime"));
        LocalDateTime endTime = LocalDateTime.parse(body.get("endTime"));
        return ResponseEntity.ok(reservationService.createReservation(userId, parkingId, startTime, endTime));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId));
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<Reservation>> getActiveReservations(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getActiveReservations(userId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
}
