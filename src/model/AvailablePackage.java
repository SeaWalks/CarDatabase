package model;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "availablePackages")
public class AvailablePackage {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "available_id")
    private int availableId;

    @Column(nullable = false)
    private int cost;

    //Bidirectional many-to-many between AvailablePackage and Automobiles.
    @ManyToMany(mappedBy = "availablePackages")
    private Set<Automobile> automobiles;
    
    //Junction For Many-To-Many between Trim and Package.
    @JoinColumn(name="trim_id")
    @ManyToOne
    private Trim trim;
    
    @JoinColumn(name="package_id")
    @ManyToOne
    private Package myPackage;
    /**************************************************************/

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


   
}
