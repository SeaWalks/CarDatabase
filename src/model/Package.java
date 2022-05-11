package model;
import java.util.Set;
import jakarta.persistence.*;

//Should be done, I think.
@Entity(name = "packages")
public class Package {
    @Id
    @JoinColumn(name = "")

    @Column(length=20)
    private String packageName;



    public Package() {
    }

    public Package(int packageId, String packageName) {
        this.packageId = packageId;
        this.packageName= packageName;
        
    }

    @Override
    public String toString() {
        return "Package: " + packageName + " (ID " + packageId + ")";
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

}
