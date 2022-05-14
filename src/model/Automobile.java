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
    @ManyToOne
    @JoinColumn(name = "trim_id")
    private Trim trim;

    public Automobile() {
    }

    public Automobile(int vin) {
        // No constructor for auto_id because it is auto-generated
        // No constructor for the set Available packages b.c we dont need it to have an
        // automobile i think?
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
