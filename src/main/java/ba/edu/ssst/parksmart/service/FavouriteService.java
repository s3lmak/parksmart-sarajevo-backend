package ba.edu.ssst.parksmart.service;

import ba.edu.ssst.parksmart.model.Favourite;
import ba.edu.ssst.parksmart.repository.FavouriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    public List<Favourite> getFavouritesByUserId(Long userId) {
        return favouriteRepository.findByUserId(userId);
    }

    public Favourite addFavourite(Long userId, Long parkingId) {
        return favouriteRepository.save(
                Favourite.builder()
                        .userId(userId)
                        .parkingId(parkingId)
                        .build()
        );
    }

    public void removeFavourite(Long userId, Long parkingId) {
        favouriteRepository.deleteByUserIdAndParkingId(userId, parkingId);
    }

    public boolean isFavourite(Long userId, Long parkingId) {
        return favouriteRepository.findByUserIdAndParkingId(userId, parkingId).isPresent();
    }
}
