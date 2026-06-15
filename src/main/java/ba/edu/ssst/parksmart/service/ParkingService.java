package ba.edu.ssst.parksmart.service;
import ba.edu.ssst.parksmart.model.Parking;
import ba.edu.ssst.parksmart.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import java.util.Map;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

private final ParkingRepository parkingRepository;
private final org.springframework.web.client.RestClient restClient =
        org.springframework.web.client.RestClient.create();


public ParkingService(ParkingRepository parkingRepository){
    this.parkingRepository = parkingRepository;
}

public List<Parking> getAllParkings(){
    return parkingRepository.findAll();
}

public Optional<Parking> getParkingById(Long id){
    return parkingRepository.findById(id);
}

public List<Parking> getParkingsByZone(String zone){
    return parkingRepository.findByZone(zone);
}

public List<Parking> getParkingsByStatus(String status){
    return parkingRepository.findByStatus(status);
}

public Parking createParking(Parking parking){
    return parkingRepository.save(parking);
}

public Parking updateParking(Long id, Parking updatedParking){
    updatedParking.setId(id);
    return parkingRepository.save(updatedParking);
}

public void deleteParking(Long id){
    parkingRepository.deleteById(id);
}

public List<Parking> getParkingsByMaxPrice(Double maxPrice) {
    return parkingRepository.findByPricePerHourLessThanEqual(maxPrice);
}

    public List<Parking> getParkingsWithLiveAvailability() {
        List<Parking> parkings = parkingRepository.findAll();

        try {
            String url = System.getenv().getOrDefault("AVAILABILITY_SERVICE_URL",
                    "https://parksmart-availability-36a105e3f6fc.herokuapp.com/api/availability") + "/batch";

            List<Map<String, Object>> requestBody = parkings.stream()
                    .map(p -> {
                        Map<String, Object> map = new java.util.HashMap<>();
                        map.put("id", p.getId());
                        map.put("totalCapacity", p.getTotalCapacity());
                        return map;
                    })
                    .collect(java.util.stream.Collectors.toList());

            List<java.util.Map<String, Object>> availabilityData = restClient.post()
                    .uri(url)
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .body(requestBody)
                    .retrieve()
                    .body(new org.springframework.core.ParameterizedTypeReference<>() {});

            if (availabilityData != null) {
                for (java.util.Map<String, Object> data : availabilityData) {
                    Long parkingId = ((Number) data.get("parkingId")).longValue();
                    int availableSpots = ((Number) data.get("availableSpots")).intValue();
                    String status = (String) data.get("status");

                    parkings.stream()
                            .filter(p -> p.getId().equals(parkingId))
                            .findFirst()
                            .ifPresent(p -> {
                                p.setAvailableSpots(availableSpots);
                                p.setStatus(status);
                            });
                }
            }
        } catch (Exception e) {
            System.out.println("Availability service error: " + e.getMessage());
        }

        return parkings;
    }

}
