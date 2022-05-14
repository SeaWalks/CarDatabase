package model;
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

    @JoinColumn(name = "trim_id")
    @ManyToOne
    private Trim trim;


    //Many-To-One between AvailablePackages->Package
    @JoinColumn(name = "package_id")
    @ManyToOne
    private Package myBigPackage; //package is a reserved word for java?


    public AvailablePackage() {
    }
    
    //I think these constructors make sense; to have available packages, you ened to know the trim you're talking about and the possible packages maybe?
    public AvailablePackage(int cost, Trim trim, Package myBigPackage) {
        this.cost = cost;
        this.trim = trim;
        this.myBigPackage = myBigPackage;
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

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public Package getPackage() {
        return myBigPackage;
    }

    public void setPackage(Package myBigPackage) {
        this.myBigPackage = myBigPackage;
    }

}
