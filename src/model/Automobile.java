package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.*;

@Entity(name = "automobiles")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automobile_id")
    private int automobileId;

    @Column(length = 17, unique = true, nullable = false)
    private String vin;

    // Bidirectional many-to-many between AvailablePackage and Automobiles.
    @ManyToMany
    @JoinTable(name = "chosenPackage", joinColumns = @JoinColumn(name = "automobile_id"), inverseJoinColumns = @JoinColumn(name = "available_id"))
    private Set<AvailablePackage> availablePackages;

    // Unidirectional many-to-one between Automobiles and Trim
    // Many automobiles have one trim. Automobiles know trim. Trim don't know
    // automobiles.
    @ManyToOne
    @JoinColumn(name = "trim_id")
    private Trim trim;

    // Constructors
    public Automobile() {
    }

    public Automobile(String vin) {
        this.vin = vin;
        this.availablePackages = new HashSet<AvailablePackage>();
    }
    
    
    /****************************/
    // "Adding Application Logic"
    /****************************/


    // public Set<Feature> getFeatures() {
    public Set<Feature> getFeatures() {
        //works BUT IS NOT ALPHABETIZED!!!!
        HashSet<Feature> s = new HashSet<Feature>();

        for (TrimFeature F : this.getTrim().getTrimFeatures()) {
            //System.out.println("Features from trims:");
            s.add(F.getFeature());
        }
        for (ModelFeature F : this.getTrim().getModel().getModelFeatures()) {
            //System.out.println("Features from Model:");
            s.add(F.getFeature());
        }
        for (AvailablePackage F : this.getAvailablePackages()) { //Look at every package
            //System.out.println("Package detected:" + F.getPackage().getPackageName());
            for (PackageFeature P : F.getPackage().getPackageFeatures()){ //Look at every package feature
                s.add(P.getFeature());
            };
        }
        return s;
    }

    public double stickerPrice() {
        // Works as intended; verified by hand.
        double total = 0;
        total += this.getTrim().getCost();
        for (AvailablePackage P : this.getAvailablePackages()) {
            total += P.getCost();
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
