package model;
import java.util.Set;
import jakarta.persistence.*;

@Entity(name = "automobiles")
public class Automobiles {
    @Id
    @Column(name = "feature_id", nullable = false) 
    private int featureId;

    private String featureName;


    @JoinTable(
        name = "modelSet",
        joinColumns = @JoinColumn(name = "feature_id"),
        inverseJoinColumns = @JoinColumn(name = "model_id")
    )

    @ManyToMany
    private Set<Model> modelSet;
    
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

    public Set<Model> getModelSet() {
        return modelSet;
    }

    public void setModelSet(Set<Model> modelSet) {
        this.modelSet = modelSet;
    }

}
