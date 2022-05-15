package model;

import jakarta.persistence.*;

@Entity(name = "PackageFeatures")
public class PackageFeature {

    // Junction For Many-To-Many between Package and Feature.
    // Multiple @Id's throws a warning in IntelliJ (wants an IDClass)

    @Id
    @JoinColumn(name = "feature_id")
    @ManyToOne
    private Feature feature;

    @Id
    @JoinColumn(name = "package_id")
    @ManyToOne
    private Package myPackage;

}