package model;

import jakarta.persistence.*;

@Entity(name = "ModelFeatures")
public class ModelFeature {

    // Junction For Many-To-Many between Model and Feature.
    // Multiple @Id's throws a warning in IntelliJ (wants an IDClass)

    @Id
    @JoinColumn(name = "feature_id")
    @ManyToOne
    private Feature feature;

    @Id
    @JoinColumn(name = "model_id")
    @ManyToOne
    private Model model;
    public ModelFeature(){

    }

    public ModelFeature(Model model, Feature feature) {
        this.feature = feature;
        this.model = model;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}