package model;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;

@Entity(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id", nullable = false) 
    private int modelId;

    private String modelName;
    private int year;

    @OneToMany
    @JoinColumn(name = "model_id")
    private List<Trim> trims;


    @ManyToMany(mappedBy = "features")
    private Set<Feature> features;

    public Model() {
    }

    public Model(int modelId, String modelName, int year) {
        this.modelId = modelId;
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

    public void setModelId(int modelId) {
        this.modelId = modelId;
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

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public List<Trim> getTrims() {
        return trims;
    }

    public void setTrims(List<Trim> trims) {
        this.trims = trims;
    }

}
