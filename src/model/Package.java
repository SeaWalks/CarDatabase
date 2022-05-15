package model;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id", nullable = false)
    private int packageId;

    @Column(length = 30)
    private String packageName;

    //Unidirectional Many-To-Many from Package->Feature w/ PackageFeature as a junction; 
    //One Package has many Features; Parent knows child, child doesn't know parents.
    @OneToMany(mappedBy = "myPackage")
    private Set<PackageFeature> packageFeatures;
    
    //No link back to availablePackages to keep it unidirectional. I think.

    public Package() {
    }

  
    //I think this is correct; doesnt make a ton of sense to have feature and trim lists included?
    public Package(String packageName) {
        this.packageName = packageName;
        this.packageFeatures = new HashSet<PackageFeature>();
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


    public Set<PackageFeature> getPackageFeatures() {
        return packageFeatures;
    }


    public void setPackageFeatures(Set<PackageFeature> packageFeatures) {
        this.packageFeatures = packageFeatures;
    }

}
