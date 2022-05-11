package model;

import jakarta.persistence.*;

@Entity(name = "packages")
public class Packages {
    @Id
    @Column(name = "feature_id", nullable = false) 
    private Feature feature;

    private String featureName;

    /*Associations.
    We need: 
    One to many with modelFeatures (JunctionTable)
    One to many with trimmFeatures (Junction)
    One to many with packageFeatures (Junction)*/

    @OneToMany
    @JoinColumn(name = "feature_id")
    private ModelFeatures modealFeatures;
    
    @OneToMany
    @JoinColumn(name = "feature_id")
    private TrimFeatures trimFeatures;

    @OneToMany
    @JoinColumn(name = "feature_id")
    private PackageFeatures packageFeatures;

    public Feature() {
    }

    public Feature(int featureId, String featureName, ModelFeatures modelFeatures, TrimFeatures trimFeatures, PackageFeatures packageFeatures) {
        this.featureId = featureId;
        this.featureName = featureName;
        this.modelFeatures = modelFeatures;
        this.trimFeatures = trimFeatures;
        this.packageFeatures = packageFeatures;
    }

    @Override
    public String toString() {
        return "Feature: " + featureName + " (ID " + featureId + ")";
    }


}
