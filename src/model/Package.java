package model;
import java.util.List;
import jakarta.persistence.*;

//Should be done, I think. 
@Entity(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id", nullable = false)
    private int packageId;

    @Column(length = 20)
    private String packageName;

    // Unidirectional One-to-Many from Package->Feature
    @OneToMany
    @JoinColumn(name = "package_id")
    private List<Feature> features;

    public Package() {
    }

  
    //I think this is correct; doesnt make a ton of sense to have feature and trim lists included?
    public Package(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "Package: " + packageName + " (ID " + packageId + ")";
    }

    public int getPackageId() {
        return packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}
