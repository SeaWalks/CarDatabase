package model;
import jakarta.persistence.*;
//Should be done.
@Entity(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id", nullable = false) 
    private int featureId;


    @Column(length=20, unique = true)
    private String featureName;


    
    public Feature() {
    }

    @Override
    public String toString() {
        return "Feature: " + featureName + " (ID " + featureId + ")";
    }

}
