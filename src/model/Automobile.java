package model;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
//Should be done.
@Entity(name = "automobiles")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automobile_id", nullable = false) 
    private int automobileId;

    @Column(unique=true)
    private int vin;


    public Automobile() {
    }

    @Override
    public String toString() {
        return "VIN: " + vin + " (ID " + automobileId + ")";
    }

}
