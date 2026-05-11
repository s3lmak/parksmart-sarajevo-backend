package ba.edu.ssst.parksmart;

import ba.edu.ssst.parksmart.model.Parking;
import ba.edu.ssst.parksmart.repository.ParkingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ParkingRepository parkingRepository;

    public DataInitializer(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public void run(String... args) {
        if (parkingRepository.count() == 0) {
            parkingRepository.save(new Parking(null, "Parking Skenderija", "Ulica Skenderija 1, Sarajevo", "Zone 1", 80, 32, "available", 1.50, "07:00 – 22:00", 43.8563, 18.4131, "https://maps.google.com/?q=43.8563,18.4131"));
            parkingRepository.save(new Parking(null, "Parking Ferhadija", "Ferhadija 12, Sarajevo", "Zone 1", 50, 4, "limited", 2.00, "00:00 – 24:00", 43.8594, 18.4322, "https://maps.google.com/?q=43.8594,18.4322"));
            parkingRepository.save(new Parking(null, "Parking Baščaršija", "Baščaršija bb, Sarajevo", "Zone 2", 40, 0, "full", 1.00, "06:00 – 23:00", 43.8607, 18.4392, "https://maps.google.com/?q=43.8607,18.4392"));
            parkingRepository.save(new Parking(null, "Parking Marijin Dvor", "Hamdije Čemerlića 2, Sarajevo", "Zone 1", 120, 67, "available", 1.50, "07:00 – 22:00", 43.8567, 18.3997, "https://maps.google.com/?q=43.8567,18.3997"));
            parkingRepository.save(new Parking(null, "Parking Čengić Vila", "Envera Šehovića 4, Sarajevo", "Zone 2", 60, 15, "limited", 1.00, "07:00 – 21:00", 43.8445, 18.3856, "https://maps.google.com/?q=43.8445,18.3856"));
            parkingRepository.save(new Parking(null, "Parking Ilidža", "Bosanski put 1, Ilidža", "Zone 3", 200, 143, "available", 0.50, "06:00 – 22:00", 43.8302, 18.3100, "https://maps.google.com/?q=43.8302,18.3100"));
        }
    }
}