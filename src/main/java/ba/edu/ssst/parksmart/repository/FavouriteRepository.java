package ba.edu.ssst.parksmart.repository;

import ba.edu.ssst.parksmart.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    List<Favourite> findByUserId(Long userId);

    Optional<Favourite> findByUserIdAndParkingId(Long userId, Long parkingId);

    @Transactional
    void deleteByUserIdAndParkingId(Long userId, Long parkingId);
}
