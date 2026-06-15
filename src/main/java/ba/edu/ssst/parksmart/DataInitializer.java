package ba.edu.ssst.parksmart;

import ba.edu.ssst.parksmart.model.Parking;
import ba.edu.ssst.parksmart.model.User;
import ba.edu.ssst.parksmart.repository.ParkingRepository;
import ba.edu.ssst.parksmart.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;

    public DataInitializer(ParkingRepository parkingRepository, UserRepository userRepository) {
        this.parkingRepository = parkingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (parkingRepository.count() == 0) {
            // STARI GRAD
            parkingRepository.save(new Parking(null, "Garaža kod Vijećnice", "Avdage Šahinagića bb, Sarajevo", "Stari Grad", 150, 0, "available", 2.0, "00:00 – 24:00", 43.8597, 18.4369, "https://maps.google.com/?q=43.8597,18.4369"));
            parkingRepository.save(new Parking(null, "Parking Dom Armije", "Zelenih beretki 14, Sarajevo", "Stari Grad", 80, 0, "available", 4.0, "00:00 – 24:00", 43.8594, 18.4284, "https://maps.google.com/?q=43.8594,18.4284"));
            parkingRepository.save(new Parking(null, "Parking Brača Mulić", "Ćurčiluk veliki 3, Sarajevo", "Stari Grad", 100, 0, "available", 2.0, "00:00 – 24:00", 43.8601, 18.4335, "https://maps.google.com/?q=43.8601,18.4335"));
            parkingRepository.save(new Parking(null, "Parking Hotel Europe", "Vladislava Skarića 5, Sarajevo", "Stari Grad", 120, 0, "available", 3.0, "00:00 – 24:00", 43.8588, 18.4296, "https://maps.google.com/?q=43.8588,18.4296"));

            // CENTAR
            parkingRepository.save(new Parking(null, "Parking Centar Skenderija", "Terezija bb, Sarajevo", "Centar", 200, 0, "available", 2.0, "00:00 – 24:00", 43.8541, 18.4124, "https://maps.google.com/?q=43.8541,18.4124"));
            parkingRepository.save(new Parking(null, "Parking Importanne Centar", "Marsala Tita 16, Sarajevo", "Centar", 300, 0, "available", 2.0, "08:00 – 22:00", 43.8571, 18.4109, "https://maps.google.com/?q=43.8571,18.4109"));
            parkingRepository.save(new Parking(null, "Parking SCC", "Vrbanja 1, Sarajevo", "Centar", 1000, 0, "available", 3.0, "00:00 – 24:00", 43.8567, 18.4019, "https://maps.google.com/?q=43.8567,18.4019"));
            parkingRepository.save(new Parking(null, "Parking ARIA Mall", "Trg djece Sarajeva bb, Sarajevo", "Centar", 500, 0, "available", 2.0, "08:00 – 22:00", 43.8558, 18.4003, "https://maps.google.com/?q=43.8558,18.4003"));
            parkingRepository.save(new Parking(null, "Parking UNITIC", "Fra Anđela Zvizdovića 1, Sarajevo", "Centar", 250, 0, "available", 2.0, "00:00 – 24:00", 43.8562, 18.4052, "https://maps.google.com/?q=43.8562,18.4052"));
            parkingRepository.save(new Parking(null, "Jadranska garaža", "Džemala Bijedića 2, Sarajevo", "Centar", 180, 0, "available", 2.0, "00:00 – 24:00", 43.8547, 18.4078, "https://maps.google.com/?q=43.8547,18.4078"));

            // MARIJIN DVOR
            parkingRepository.save(new Parking(null, "Parking Marijin Dvor", "Hamdije Čemerlića 2, Sarajevo", "Marijin Dvor", 120, 0, "available", 2.0, "07:00 – 22:00", 43.8567, 18.3997, "https://maps.google.com/?q=43.8567,18.3997"));
            parkingRepository.save(new Parking(null, "Parking Hotel Holiday", "Zmaja od Bosne 4, Sarajevo", "Marijin Dvor", 150, 0, "available", 3.0, "00:00 – 24:00", 43.8558, 18.3975, "https://maps.google.com/?q=43.8558,18.3975"));
            parkingRepository.save(new Parking(null, "Parking Sarajevo Tower", "Marsala Tita 9a, Sarajevo", "Marijin Dvor", 200, 0, "available", 2.0, "00:00 – 24:00", 43.8574, 18.4015, "https://maps.google.com/?q=43.8574,18.4015"));

            // NOVO SARAJEVO / GRBAVICA
            parkingRepository.save(new Parking(null, "Parking Avaz Twist Tower", "Teška 12, Sarajevo", "Novo Sarajevo", 200, 0, "available", 2.0, "00:00 – 24:00", 43.8529, 18.3927, "https://maps.google.com/?q=43.8529,18.3927"));
            parkingRepository.save(new Parking(null, "Parking Merkur Otoka", "Džemala Bijedića 2, Sarajevo", "Novo Sarajevo", 300, 0, "available", 1.0, "08:00 – 22:00", 43.8472, 18.3789, "https://maps.google.com/?q=43.8472,18.3789"));
            parkingRepository.save(new Parking(null, "Parking Bingo Otoka", "Džemala Bijedića 4, Sarajevo", "Novo Sarajevo", 250, 0, "available", 1.0, "08:00 – 22:00", 43.8468, 18.3801, "https://maps.google.com/?q=43.8468,18.3801"));
            parkingRepository.save(new Parking(null, "Parking TC Konzum Koševo", "Patriotske lige 45, Sarajevo", "Novo Sarajevo", 150, 0, "available", 1.0, "08:00 – 21:00", 43.8612, 18.4058, "https://maps.google.com/?q=43.8612,18.4058"));
            parkingRepository.save(new Parking(null, "Parking BOSMAN", "Vrbanja 2, Sarajevo", "Novo Sarajevo", 80, 0, "available", 2.0, "00:00 – 24:00", 43.8563, 18.4025, "https://maps.google.com/?q=43.8563,18.4025"));

            // NOVI GRAD / NEDŽARIĆI
            parkingRepository.save(new Parking(null, "Garaža Alta Shopping Center", "Džemala Bijedića 185, Sarajevo", "Novi Grad", 400, 0, "available", 1.0, "08:00 – 22:00", 43.8397, 18.3714, "https://maps.google.com/?q=43.8397,18.3714"));
            parkingRepository.save(new Parking(null, "Parking Hotel Radon Plaza", "Džemala Bijedića 191, Sarajevo", "Nedžarići", 200, 0, "available", 3.0, "00:00 – 24:00", 43.8388, 18.3698, "https://maps.google.com/?q=43.8388,18.3698"));
            parkingRepository.save(new Parking(null, "Parking Hotel Hills", "Džemala Bijedića 167, Sarajevo", "Nedžarići", 180, 0, "available", 3.0, "00:00 – 24:00", 43.8401, 18.3731, "https://maps.google.com/?q=43.8401,18.3731"));

            // ILIDŽA
            parkingRepository.save(new Parking(null, "Bingo City Center Ilidža", "Bosanski put 4, Ilidža", "Ilidža", 300, 0, "available", 1.0, "08:00 – 22:00", 43.8302, 18.3100, "https://maps.google.com/?q=43.8302,18.3100"));
            parkingRepository.save(new Parking(null, "Parking Grand Centar Ilidža", "Bosanski put 6, Ilidža", "Ilidža", 200, 0, "available", 1.0, "08:00 – 22:00", 43.8298, 18.3089, "https://maps.google.com/?q=43.8298,18.3089"));
            parkingRepository.save(new Parking(null, "Parking Terminal Ilidža", "Regulaciona 4, Ilidža", "Ilidža", 150, 0, "available", 1.0, "00:00 – 24:00", 43.8285, 18.3072, "https://maps.google.com/?q=43.8285,18.3072"));

            // AERODROM
            parkingRepository.save(new Parking(null, "ZONA Parking Aerodrom", "Davorina Popovića 10, Sarajevo", "Aerodrom", 500, 0, "available", 5.0, "00:00 – 24:00", 43.8246, 18.3315, "https://maps.google.com/?q=43.8246,18.3315"));
            parkingRepository.save(new Parking(null, "Simply Parking Aerodrom", "Davorina Popovića 12, Sarajevo", "Aerodrom", 300, 0, "available", 4.0, "00:00 – 24:00", 43.8241, 18.3308, "https://maps.google.com/?q=43.8241,18.3308"));

            // DOBRINJA
            parkingRepository.save(new Parking(null, "Mercator Dobrinja", "Dobrinjske bolnice 4, Sarajevo", "Dobrinja", 250, 0, "available", 1.0, "08:00 – 22:00", 43.8197, 18.3489, "https://maps.google.com/?q=43.8197,18.3489"));

            // MEJTAŠ
            parkingRepository.save(new Parking(null, "Parking Campus UNSA/ETF", "Zmaja od Bosne 8, Sarajevo", "Mejtaš", 100, 0, "available", 1.0, "07:00 – 21:00", 43.8561, 18.3961, "https://maps.google.com/?q=43.8561,18.3961"));
            parkingRepository.save(new Parking(null, "Parking ASA Bolnica", "Džemala Bijedića 1, Sarajevo", "Mejtaš", 120, 0, "available", 2.0, "00:00 – 24:00", 43.8489, 18.3812, "https://maps.google.com/?q=43.8489,18.3812"));

            // ŽELJEZNIČKE/AUTOBUSKE
            parkingRepository.save(new Parking(null, "Parking Željeznička stanica", "Put života 2, Sarajevo", "Centar", 100, 0, "available", 2.0, "00:00 – 24:00", 43.8474, 18.3867, "https://maps.google.com/?q=43.8474,18.3867"));
            parkingRepository.save(new Parking(null, "Parking Autobuska stanica", "Put života 8, Sarajevo", "Centar", 150, 0, "available", 2.0, "00:00 – 24:00", 43.8467, 18.3873, "https://maps.google.com/?q=43.8467,18.3873"));
        }

        if (userRepository.findByEmail("admin@parksmart.ba").isEmpty()) {
            userRepository.save(User.builder()
                    .fullName("Admin")
                    .email("admin@parksmart.ba")
                    .password(encoder.encode("admin123"))
                    .role("ADMIN")
                    .build());
        }
    }
}