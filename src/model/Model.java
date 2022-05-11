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

    @Column(length=20)
    private String modelName;
    private int year;

    //Parent of uni-Directional One-to-many with Trim (parent)
    //A model should know its trims; trims do not need to know their parent. I think.
    @OneToMany
    @JoinColumn(name = "model_id")
    private List<Trim> trims;

    //Many-to-many with features.
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
