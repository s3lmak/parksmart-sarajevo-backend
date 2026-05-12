package ba.edu.ssst.parksmart.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parkings")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String zone;
    private Integer totalCapacity;
    private Integer availableSpots;
    private String status;
    private Double pricePerHour;
    private String workingHours;
    private Double latitude;
    private Double longitude;
    private String googleMapsUrl;

}
