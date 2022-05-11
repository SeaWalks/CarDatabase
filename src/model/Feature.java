package model;
import java.util.Set;
import jakarta.persistence.*;
//Should be done, I think.
@Entity(name = "features")
public class Feature {
    @Id
    @Column(name = "feature_id", nullable = false) 
    private int featureId;

    private String featureName;


    @JoinTable(
        name = "models",
        joinColumns = @JoinColumn(name = "feature_id"),
        inverseJoinColumns = @JoinColumn(name = "model_id")
    )

    @ManyToMany
    private Set<Model> models;
    
    public Feature() {
    }

    public Feature(int featureId, String featureName) {
        this.featureId = featureId;
        this.featureName = featureName;
    }

    @Override
    public String toString() {
        return "Feature: " + featureName + " (ID " + featureId + ")";
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Set<Model> getmodels() {
        return models;
    }

    public void setmodels(Set<Model> models) {
        this.models = models;
    }

}
