package ba.edu.ssst.parksmart.service;

import ba.edu.ssst.parksmart.model.Favourite;
import ba.edu.ssst.parksmart.repository.FavouriteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavouriteServiceTest {

    @Mock
    private FavouriteRepository favouriteRepository;

    @InjectMocks
    private FavouriteService favouriteService;

    @Test
    void getFavouritesByUserId_returnsListOfFavourites() {
        Favourite fav1 = Favourite.builder().id(1L).userId(10L).parkingId(100L).build();
        Favourite fav2 = Favourite.builder().id(2L).userId(10L).parkingId(200L).build();
        when(favouriteRepository.findByUserId(10L)).thenReturn(List.of(fav1, fav2));

        List<Favourite> result = favouriteService.getFavouritesByUserId(10L);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getParkingId()).isEqualTo(100L);
        assertThat(result.get(1).getParkingId()).isEqualTo(200L);
        verify(favouriteRepository).findByUserId(10L);
    }

    @Test
    void addFavourite_savesAndReturnsFavourite() {
        Favourite saved = Favourite.builder().id(1L).userId(10L).parkingId(100L).build();
        when(favouriteRepository.save(any(Favourite.class))).thenReturn(saved);

        Favourite result = favouriteService.addFavourite(10L, 100L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUserId()).isEqualTo(10L);
        assertThat(result.getParkingId()).isEqualTo(100L);

        ArgumentCaptor<Favourite> captor = ArgumentCaptor.forClass(Favourite.class);
        verify(favouriteRepository).save(captor.capture());
        assertThat(captor.getValue().getUserId()).isEqualTo(10L);
        assertThat(captor.getValue().getParkingId()).isEqualTo(100L);
        assertThat(captor.getValue().getId()).isNull();
    }

    @Test
    void removeFavourite_callsDeleteByUserIdAndParkingId() {
        doNothing().when(favouriteRepository).deleteByUserIdAndParkingId(10L, 100L);

        favouriteService.removeFavourite(10L, 100L);

        verify(favouriteRepository).deleteByUserIdAndParkingId(10L, 100L);
        verifyNoMoreInteractions(favouriteRepository);
    }

    @Test
    void isFavourite_returnsTrue_whenFavouriteExists() {
        Favourite fav = Favourite.builder().id(1L).userId(10L).parkingId(100L).build();
        when(favouriteRepository.findByUserIdAndParkingId(10L, 100L)).thenReturn(Optional.of(fav));

        boolean result = favouriteService.isFavourite(10L, 100L);

        assertThat(result).isTrue();
        verify(favouriteRepository).findByUserIdAndParkingId(10L, 100L);
    }

    @Test
    void isFavourite_returnsFalse_whenFavouriteNotFound() {
        when(favouriteRepository.findByUserIdAndParkingId(10L, 999L)).thenReturn(Optional.empty());

        boolean result = favouriteService.isFavourite(10L, 999L);

        assertThat(result).isFalse();
        verify(favouriteRepository).findByUserIdAndParkingId(10L, 999L);
    }
}
