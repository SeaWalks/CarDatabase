package model;

import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "availablePackages")
public class AvailablePackage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "available_id")
    private int availableId;

    @Column(nullable = false)
    private int cost; 

    @Column(length = 100, unique = true, nullable = false)
    private String name;



    public AvailablePackage() {
    }


    @Override
    public String toString() {
        return "Visitor " + name + " (ID " + visitorId + ")";
    }


}
