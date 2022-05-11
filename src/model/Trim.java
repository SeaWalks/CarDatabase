package model;

import jakarta.persistence.*;

@Entity(name = "trims")
public class Trim {
    @Id
    @JoinColumn(name = "feature_id") 
    @ManyToOne
    private Feature feature;

    @Id
    @JoinColumn(name = "feature_id") 
    @ManyToOne
    private int featureId;

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
