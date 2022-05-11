package model;
import java.util.List;
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

    // Unidirectional One-To-Many from Model->Feature
    @OneToMany
    @JoinColumn(name = "feature_id")
    private List<Feature> features;

    //BiDirectional  One-To-Many between Model & Trim
    //One model has many trims
    @OneToMany(mappedBy = "trims")
    private List<Trim> trims;

    public Model() {
    }

    //Not sure if we need to include more stuff in header (feature List and trim List)
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

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Trim> getTrims() {
        return trims;
    }

    public void setTrims(List<Trim> trims) {
        this.trims = trims;
    }
    
}
