package model;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "automobiles")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automobile_id")
    private int automobileId;

    @Column(length = 17, unique = true, nullable = false)
    private String vin;

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

    //Constructors
    public Automobile() {
    }

    public Automobile(String vin) {
        this.vin = vin;
    }

    //"Adding Application Logic"
    public Set<Feature> getFeatures(){
        //
        Set featureSet = new HashSet<Feature>();
        this.getTrim().getTrimFeatures();
        this.getTrim().getModel().getModelFeatures();
        this.getAvailablePackages().

    }
    public double stickerPrice(){
        //Needs to be tested.
        double total = 0;
        total+=this.getTrim().getCost();
        Set<AvailablePackage> allPackages = this.getAvailablePackages();
        Iterator<AvailablePackage> packageIterator = allPackages.iterator();
        while(packageIterator.hasNext()){
            total+=packageIterator.next().getCost();
        }
        return total;
    }
    @Override
    public String toString() {
        return "VIN: " + vin + " (ID " + automobileId + ")";
    }

    public int getAutomobileId() {
        return automobileId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Set<AvailablePackage> getAvailablePackages() {
        return availablePackages;
    }

    public void setAvailablePackages(Set<AvailablePackage> availablePackages) {
        this.availablePackages = availablePackages;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }


}
