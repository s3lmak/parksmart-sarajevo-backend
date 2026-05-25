package ba.edu.ssst.parksmart.service;

import ba.edu.ssst.parksmart.model.Parking;
import ba.edu.ssst.parksmart.repository.ParkingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingServiceTest {

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    private ParkingService parkingService;

    private Parking parking;

    @BeforeEach
    void setUp() {
        parking = new Parking();
        parking.setId(1L);
        parking.setName("Parking Skenderija");
        parking.setZone("Zone 1");
        parking.setStatus("available");
        parking.setPricePerHour(1.5);
        parking.setAvailableSpots(32);
        parking.setTotalCapacity(80);
    }

    // getAllParkings
    @Test
    void getAllParkings_shouldReturnAllParkings() {
        when(parkingRepository.findAll()).thenReturn(List.of(parking));
        List<Parking> result = parkingService.getAllParkings();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Parking Skenderija");
    }

    @Test
    void getAllParkings_shouldReturnEmptyList_whenNoParkings() {
        when(parkingRepository.findAll()).thenReturn(List.of());
        List<Parking> result = parkingService.getAllParkings();
        assertThat(result).isEmpty();
    }

    // getParkingById
    @Test
    void getParkingById_shouldReturnParking_whenExists() {
        when(parkingRepository.findById(1L)).thenReturn(Optional.of(parking));
        Optional<Parking> result = parkingService.getParkingById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
    }

    @Test
    void getParkingById_shouldReturnEmpty_whenNotExists() {
        when(parkingRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Parking> result = parkingService.getParkingById(99L);
        assertThat(result).isEmpty();
    }

    // getParkingsByZone
    @Test
    void getParkingsByZone_shouldReturnParkingsInZone() {
        when(parkingRepository.findByZone("Zone 1")).thenReturn(List.of(parking));
        List<Parking> result = parkingService.getParkingsByZone("Zone 1");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getZone()).isEqualTo("Zone 1");
    }

    @Test
    void getParkingsByZone_shouldReturnEmpty_whenZoneNotFound() {
        when(parkingRepository.findByZone("Zone 99")).thenReturn(List.of());
        List<Parking> result = parkingService.getParkingsByZone("Zone 99");
        assertThat(result).isEmpty();
    }

    // getParkingsByStatus
    @Test
    void getParkingsByStatus_shouldReturnAvailableParkings() {
        when(parkingRepository.findByStatus("available")).thenReturn(List.of(parking));
        List<Parking> result = parkingService.getParkingsByStatus("available");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatus()).isEqualTo("available");
    }

    @Test
    void getParkingsByStatus_shouldReturnEmpty_whenNoMatch() {
        when(parkingRepository.findByStatus("full")).thenReturn(List.of());
        List<Parking> result = parkingService.getParkingsByStatus("full");
        assertThat(result).isEmpty();
    }

    // createParking
    @Test
    void createParking_shouldSaveAndReturnParking() {
        when(parkingRepository.save(parking)).thenReturn(parking);
        Parking result = parkingService.createParking(parking);
        assertThat(result.getName()).isEqualTo("Parking Skenderija");
        verify(parkingRepository, times(1)).save(parking);
    }

    // updateParking
    @Test
    void updateParking_shouldUpdateAndReturnParking() {
        Parking updated = new Parking();
        updated.setName("Parking Updated");
        when(parkingRepository.save(any(Parking.class))).thenReturn(updated);
        Parking result = parkingService.updateParking(1L, updated);
        assertThat(result.getName()).isEqualTo("Parking Updated");
        verify(parkingRepository, times(1)).save(updated);
    }

    // deleteParking
    @Test
    void deleteParking_shouldCallDeleteById() {
        doNothing().when(parkingRepository).deleteById(1L);
        parkingService.deleteParking(1L);
        verify(parkingRepository, times(1)).deleteById(1L);
    }

    // getParkingsByMaxPrice
    @Test
    void getParkingsByMaxPrice_shouldReturnParkingsUnderMaxPrice() {
        when(parkingRepository.findByPricePerHourLessThanEqual(2.0)).thenReturn(List.of(parking));
        List<Parking> result = parkingService.getParkingsByMaxPrice(2.0);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getPricePerHour()).isLessThanOrEqualTo(2.0);
    }

    @Test
    void getParkingsByMaxPrice_shouldReturnEmpty_whenAllParkingsAboveMaxPrice() {
        when(parkingRepository.findByPricePerHourLessThanEqual(0.5)).thenReturn(List.of());
        List<Parking> result = parkingService.getParkingsByMaxPrice(0.5);
        assertThat(result).isEmpty();
    }
}