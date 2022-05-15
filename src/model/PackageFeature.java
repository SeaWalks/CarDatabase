package model;
import jakarta.persistence.*;

//Junction For Many-To-Many between Package and Feature.
//Multiple @Id's throws a warning in IntelliJ (wants an IDClass)

@Entity(name = "PackageFeatures")
public class PackageFeature {
    @Id
    @JoinColumn(name="feature_id")
    @ManyToOne
    private Feature feature;
    
    @Id
    @JoinColumn(name="package_id")
    @ManyToOne
    private Package myPackage;

}