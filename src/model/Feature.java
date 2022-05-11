package model;
import java.util.Set;
import jakarta.persistence.*;
//Should be done, I think.
@Entity(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id", nullable = false) 
    private int featureId;

    private String featureName;

    //Many-to-many association with models
    @JoinTable(
        name = "models",
        joinColumns = @JoinColumn(name = "feature_id"),
        inverseJoinColumns = @JoinColumn(name = "model_id")
    )
    @ManyToMany
    private Set<Model> models;
    
    //Many-to-Many association with Trims 
    @JoinTable(
        name = "trims",
        joinColumns = @JoinColumn(name = "feature_id"),
        inverseJoinColumns = @JoinColumn(name = "trim_id")
    )
    @ManyToMany
    private Set<Trim> trims;

    //Many-to-Many association with Packages
    @JoinTable(
        name = "packages",
        joinColumns = @JoinColumn(name = "feature_id"),
        inverseJoinColumns = @JoinColumn(name = "package_id")
    )
    @ManyToMany
    private Set<Package> packages;

    
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

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    public Set<Trim> getTrims() {
        return trims;
    }

    public void setTrims(Set<Trim> trims) {
        this.trims = trims;
    }

    public Set<Package> getPackages() {
        return packages;
    }

    public void setPackages(Set<Package> packages) {
        this.packages = packages;
    }

}
