package ba.edu.ssst.parksmart.controller;

import ba.edu.ssst.parksmart.model.Parking;
import ba.edu.ssst.parksmart.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkings")
@CrossOrigin(origins = {"http://localhost:4200", "https://parksmart-sarajevo.netlify.app"})
public class ParkingController {
    private final ParkingService parkingService;
    private ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    @GetMapping
    public ResponseEntity<List<Parking>> getAllParkings() {
        return ResponseEntity.ok(parkingService.getParkingsWithLiveAvailability());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parking> getParkingById(@PathVariable Long id) {
        return parkingService.getParkingsWithLiveAvailability()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/zone/{zone}")
    public List<Parking> getParkingsByZone(@PathVariable String zone){
            return parkingService.getParkingsByZone(zone);
    }

    @GetMapping("/status/{status}")
    public List<Parking> getParkingsByStatus(@PathVariable String status){
        return parkingService.getParkingsByStatus(status);
    }

    @PostMapping
    public Parking createParking(@RequestBody Parking parking){
        return parkingService.createParking(parking);
    }

    @PutMapping("/{id}")
    public Parking updateParking(@PathVariable Long id, @RequestBody Parking parking){
        return parkingService.updateParking(id, parking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParking(Long id){
        parkingService.deleteParking(id);
        return ResponseEntity.noContent().build();
    }



}
