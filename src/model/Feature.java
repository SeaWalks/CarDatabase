package model;
import jakarta.persistence.*;
//Should be done.
@Entity(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id") 
    private int featureId;

    @Column(length=20, unique = true, nullable = false)
    private String featureName;

    public Feature() {
    }

    public Feature(String featureName) {
        this.featureName = featureName;
    }

    @Override
    public String toString() {
        return "Feature: " + featureName + " (ID " + featureId + ")";
    }

    public int getFeatureId() {
        return featureId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

}
