package model;
import jakarta.persistence.*;

//Junction For Many-To-Many between Trim and Feature.
//Multiple @Id's throws a warning in IntelliJ (wants an IDClass)

@Entity(name = "TrimFeatures")
public class TrimFeature {
    @Id
    @JoinColumn(name="feature_id")
    @ManyToOne
    private Feature feature;
    
    @Id
    @JoinColumn(name="trim_id")
    @ManyToOne
    private Trim trim;

}