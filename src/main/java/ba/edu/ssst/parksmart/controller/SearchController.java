package ba.edu.ssst.parksmart.controller;

import ba.edu.ssst.parksmart.model.Parking;
import ba.edu.ssst.parksmart.service.ParkingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    private final ParkingService parkingService;

    public SearchController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/zone/{zone}")
    public List<Parking> searchByZone(@PathVariable String zone) {
        return parkingService.getParkingsByZone(zone);
    }

    @GetMapping("/status/{status}")
    public List<Parking> searchByStatus(@PathVariable String status) {
        return parkingService.getParkingsByStatus(status);
    }

    @GetMapping("/price")
    public List<Parking> searchByMaxPrice(@RequestParam Double maxPrice) {
        return parkingService.getParkingsByMaxPrice(maxPrice);
    }
}