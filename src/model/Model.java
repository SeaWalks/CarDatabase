package model;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "models")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "modelName", "year" }) })
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private int modelId;

    @Column(length = 20, nullable = false)
    private String modelName;

    @Column(nullable = false)
    private int year;

     //Unidirectional Many-To-Many from Model->Feature w/ ModelFeature as a junction; 
    //One Model has many Features; Parent knows child, child doesn't know parents.
    @OneToMany(mappedBy = "model")
    private Set<ModelFeature> modelFeatures;


    //Bi-directional One-To-Many between Model->Trim
    //One Model has many Trims; parent and children know each other.
    @OneToMany(mappedBy = "model")
    private List<Trim> trims;

    public Model() {
    }

    public Model(String modelName, int year) {
        this.modelName = modelName;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Model: " + modelName + " (ID " + modelId + ")";
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<ModelFeature> getModelFeatures() {
        return modelFeatures;
    }

    public void setModelFeatures(Set<ModelFeature> modelFeatures) {
        this.modelFeatures = modelFeatures;
    }

    public List<Trim> getTrims() {
        return trims;
    }

    public void setTrims(List<Trim> trims) {
        this.trims = trims;
    }
    
}
