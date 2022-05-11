package model;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;

//Should be done, I think. 
@Entity(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id", nullable = false) 
    private int packageId;

    @Column(length=20)
    private String packageName;

    //Unidirectional One-to-Many from Package->Feature
    @OneToMany
    @JoinColumn(name="feature_id")
    private List<Feature> features;

    public Package() {
    }

    public Package(int packageId, String packageName) {
        this.packageId = packageId;
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "Package: " + packageName + " (ID " + packageId + ")";
    }

}
