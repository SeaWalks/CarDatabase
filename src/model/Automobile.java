package model;
import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "automobiles")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automobile_id")
    private int automobileId;

    @Column(unique = true, nullable = false)
    private int vin;

    //Bidirectional many-to-many between AvailablePackage and Automobiles.
    @ManyToMany
    @JoinTable(
        name = "chosenPackage",
        joinColumns = @JoinColumn(name= "automobile_id"),
        inverseJoinColumns = @JoinColumn(name="available_id")
    )
    private Set<AvailablePackage> availablePackages;

    //Unidirectional many-to-one between Automobiles and Trim
    //Many automobiles have one trim. Automobiles know trim. Trim don't know automobiles.
    @ManyToOne
    @JoinColumn(name="trim_id")
    private Trim trim;


    public Automobile() {
    }

    public Automobile(int vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "VIN: " + vin + " (ID " + automobileId + ")";
    }

    public int getAutomobileId() {
        return automobileId;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public Set<AvailablePackage> getAvailablePackages() {
        return availablePackages;
    }

    public void setAvailablePackages(Set<AvailablePackage> availablePackages) {
        this.availablePackages = availablePackages;
    }

}
