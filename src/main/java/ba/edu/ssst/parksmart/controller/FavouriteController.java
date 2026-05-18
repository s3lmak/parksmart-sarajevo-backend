package ba.edu.ssst.parksmart.controller;

import ba.edu.ssst.parksmart.model.Favourite;
import ba.edu.ssst.parksmart.service.FavouriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favourites")
@CrossOrigin(origins = "*")
public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favourite>> getFavouritesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favouriteService.getFavouritesByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Favourite> addFavourite(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        Long parkingId = body.get("parkingId");
        return ResponseEntity.ok(favouriteService.addFavourite(userId, parkingId));
    }

    @DeleteMapping("/{userId}/{parkingId}")
    public ResponseEntity<Void> removeFavourite(@PathVariable Long userId, @PathVariable Long parkingId) {
        favouriteService.removeFavourite(userId, parkingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/{parkingId}")
    public ResponseEntity<Boolean> isFavourite(@PathVariable Long userId, @PathVariable Long parkingId) {
        return ResponseEntity.ok(favouriteService.isFavourite(userId, parkingId));
    }
}
