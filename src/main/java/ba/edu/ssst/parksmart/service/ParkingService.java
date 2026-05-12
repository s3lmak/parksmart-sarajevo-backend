package ba.edu.ssst.parksmart.service;
import ba.edu.ssst.parksmart.model.Parking;
import ba.edu.ssst.parksmart.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

private final ParkingRepository parkingRepository;

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

}
