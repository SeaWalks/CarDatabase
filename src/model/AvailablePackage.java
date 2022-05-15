package model;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "availablePackages")
public class AvailablePackage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "available_id")
    private int availableId;

    @Column(nullable = false)
    private int cost;

    //Unidirectional One-To-Many from AvailablePackages->Packages
    //AvailablePackage has many packages; Parent knows children, children don't know parent.
    @OneToMany
    @JoinColumn(name = "package_id")
    private List<Package> packages; //package is a reserved word for java?


    public AvailablePackage() {
    }
    
    //I think these constructors make sense; to have available packages, you ened to know the trim you're talking about and the possible packages maybe?
    public AvailablePackage(int cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "Available Package Cost: " + cost + " (ID " + availableId + ")";
    }

    public int getAvailableId() {
        return availableId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

   
}
