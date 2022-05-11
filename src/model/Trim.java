package model;

import java.util.List;

import jakarta.persistence.*;

@Entity(name = "trims")
public class Trim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trim_id", nullable = false) 
    private int trimId;
    private String trimName;
    private int cost;

    //Unidirectional Many-To-One- a child doesn't need to know its parent
    //A trim does not need to know its model?
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    //Unidirectional One-To-Many: Parents know their children, children don't know parents.
    //A trim does not need to know its automobiles
    @OneToMany
    @JoinColumn(name = "trim_id")
    private List<Automobile> automobiles;

    //Unidirectional One-To-Many: Parents know their children, children don't know parents.
    @OneToMany
    @JoinColumn(name = "trim_id")
    private List<AvailablePackage> availablePackages;
    
    public Trim() {
    }

    public Trim(int trimId, String trimName, int cost, Model model) {
        this.trimId = trimId;
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

    public void setTrimId(int trimId) {
        this.trimId = trimId;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<Automobile> getAutomobiles() {
        return automobiles;
    }

    public void setAutomobiles(List<Automobile> automobiles) {
        this.automobiles = automobiles;
    }

    public List<AvailablePackage> getAvailablePackages() {
        return availablePackages;
    }

    public void setAvailablePackages(List<AvailablePackage> availablePackages) {
        this.availablePackages = availablePackages;
    }
}
