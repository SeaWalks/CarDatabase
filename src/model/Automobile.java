package model;

import java.util.Collections;
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

    // "Adding Application Logic"

    public Set<Feature> getFeatures() {
        //Does not work as intended.
        Set<Feature> featureSet = new Set();
        // Get features from trim
        featureSet.addAll(this.getTrim().getTrimFeatures());
        // Get features from model
        featureSet.addAll(this.getTrim().getModel().getModelFeatures());
        // Get features from all installed packages
        for (AvailablePackage P : this.getAvailablePackages()) {
            featureSet.addAll(P.getPackage().getPackageFeatures());
        }
    }

    public double stickerPrice() {
        // Works as intended.
        double total = 0;
        total += this.getTrim().getCost();
        System.out.println("TRIMID: " + this.getTrim().getTrimId());
        for (AvailablePackage P : this.getAvailablePackages()) {
            System.out.println("AP ID: " + P.getAvailableId() + P.getPackage().getPackageName());
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
