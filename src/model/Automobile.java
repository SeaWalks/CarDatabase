package model;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;

@Entity(name = "automobiles")
public class Automobile {
    @Id
    @Column(name = "automobile_id", nullable = false) 
    private int automobileId;

    @Column(unique=true)
    private int vin;

   //Bidirectional @OneToMany with trims
    @OneToMany(mappedBy = "trim_id")
    private List<Trim> trims;

    //ManyToMany with available
    @ManyToMany(mappedBy = "availablePackages")
    private Set<AvailablePackage> availablePackages;
    

    public Automobile() {
    }

    public Automobile(int automobileId, int vin) {
        this.automobileId = automobileId;
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "VIN: " + vin + " (ID " + automobileId + ")";
    }

    public Automobile(int automobileId, int vin, List<Trim> trims, Set<AvailablePackage> availablePackages) {
        this.automobileId = automobileId;
        this.vin = vin;
        this.trims = trims;
        this.availablePackages = availablePackages;
    }

    public int getAutomobileId() {
        return automobileId;
    }

    public void setAutomobileId(int automobileId) {
        this.automobileId = automobileId;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public List<Trim> getTrims() {
        return trims;
    }

    public void setTrims(List<Trim> trims) {
        this.trims = trims;
    }

    public Set<AvailablePackage> getAvailablePackages() {
        return availablePackages;
    }

    public void setAvailablePackages(Set<AvailablePackage> availablePackages) {
        this.availablePackages = availablePackages;
    }
   
}
