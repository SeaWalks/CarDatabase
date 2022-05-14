package model;

import java.util.List;
import jakarta.persistence.*;

@Entity(name = "trims")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "model_id", "trimName" }) })
public class Trim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trim_id")
    private int trimId;

    @Column(nullable = false)
    private String trimName;

    @Column(nullable = false)
    private int cost;

    // Unidirectional One-To-Many from Trim->Feature
    @OneToMany
    @JoinColumn(name = "feature_id")
    private List<Feature> features;

    // Bidirectional Many-To-One between Trim & Model; Model = parent, trim = child
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    public Trim() {
    }

    public Trim(String trimName, int cost, Model model) {
        this.trimName = trimName;
        this.cost = cost;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Trim: " + trimName + " (ID " + trimId + ")";
    }

    public int getTrimId() {
        return trimId;
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
