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

    //Many-To-Many relationship between Automobiles and Available Packages.
    @ManyToMany
    @JoinTable(
        name = "ChosenPackage",                                     //Name of junction table; ChosenPackage used.
        joinColumns = @JoinColumn(name = "auto_id"),                //Name of key from this (automobile) table
        inverseJoinColumns = @JoinColumn(name = "available_id")     //Name of key from AvailablePackage table
    )
    private Set<AvailablePackage> availablePackages;

    public Automobile() {
    }

    public Automobile(int vin) {
        //No constructor for auto_id because it is auto-generated
        //No constructor for the set Available packages b.c we dont need it to have an automobile i think?
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

    public Set<AvailablePackage> getAvailablePackages() {
        return availablePackages;
    }

    public void setAvailablePackages(Set<AvailablePackage> availablePackages) {
        this.availablePackages = availablePackages;
    }

}
