package model;

import java.util.List;
import jakarta.persistence.*;

@Entity(name = "models")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "modelName", "year" }) })
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id", nullable = false)
    private int modelId;

    @Column(length = 20)
    private String modelName;
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


    @Override
    public String toString() {
        return "Model: " + modelName + " (ID " + modelId + ")";
    }

    
}
