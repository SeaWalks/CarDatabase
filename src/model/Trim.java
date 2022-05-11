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

    // Unidirectional Many-To-One from Automobile->Trim
    @ManyToOne
    @JoinColumn(name = "trim_id")
    private Automobile automobile;

    // Bidirectional Many-To-One between Trim & Model; Model = parent, trim = child
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    public Trim() {
    }

    @Override
    public String toString() {
        return "Trim: " + trimName + " (ID " + trimId + ")";
    }

}
