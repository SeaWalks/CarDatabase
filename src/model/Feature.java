package model;

import jakarta.persistence.*;

@Entity(name = "Feature")
public class Feature {
    @Id
    @Column(name = "feature_id", nullable = false) 
    private int featureId;

    private String featureName;

    // The bidirectional link back to the parent Museum.
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
