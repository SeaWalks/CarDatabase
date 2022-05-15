package model;
import jakarta.persistence.*;

@Entity(name = "automobiles")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automobile_id")
    private int automobileId;

    @Column(unique = true, nullable = false)
    private int vin;

    // Unidirectional Many-To-One from Automobile->Trim
    // Automobile has many trims; parent knows children, children doesn't know parent.
    @ManyToOne
    @JoinColumn(name = "trim_id")
    private Trim trim;

    public Automobile() {
    }

    public Automobile(int vin) {
        // No constructor for auto_id because it is auto-generated
        // No constructor trim b/c we don't need it i think
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "VIN: " + vin + " (ID " + automobileId + ")";
    }

    public int getAutomobileId() {
        return automobileId;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

}
