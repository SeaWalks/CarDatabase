package model;

import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "availablePackages")
public class AvailablePackage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "visitor_id")
    private int visitorId;

    @Column(length = 100, unique = true)
    private String name;

    // Many-to-many with Museums, via the assocation (junction) class MuseumVisit.
    @OneToMany(mappedBy = "visitor")
    private Set<MuseumVisit> visits;

    // Many-to-many with Museums, with no association class.
    @ManyToMany(mappedBy = "members")
    private Set<Museum> memberships;

    public AvailablePackage() {
    }


    @Override
    public String toString() {
        return "Visitor " + name + " (ID " + visitorId + ")";
    }


}
