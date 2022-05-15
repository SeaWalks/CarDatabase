import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.persistence.internal.jpa.deployment.PersistenceUnitProcessor.Mode;

import jakarta.persistence.*;
import model.*;
import model.Package; //Explicit import is good for "Package"

public class App {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cardb");
        EntityManager em = factory.createEntityManager();
        //Add Features to database
        em.getTransaction().begin();
        Feature leatherSeats = new Feature("Leather Seats");
        Feature hybridEngine = new Feature("Plug-in hybrid engine");
        Feature powerDoors = new Feature("Power sliding doors");
        Feature handsFreeDoors = new Feature("Hands-free sliding doors");
        Feature fireTV = new Feature("Amazon FireTV");
        Feature rearScreens = new Feature("Rear-Seat Entertainment Screens");
        em.persist(leatherSeats);
        Feature awd = new Feature("All-Wheel Drive");
        Feature adaptiveCruise = new Feature("Adaptive Cruise Control");
        //Add Packages to database
        Package theater = new Package("Theater Package");
        Package amazon = new Package("Amazon Theater Package");
        Package safety = new Package ("Safety Package");
        //Add PackageFeatures to database
        PackageFeature a1 = new PackageFeature(theater, rearScreens);
        PackageFeature a2 = new PackageFeature(amazon, rearScreens);
        PackageFeature a3 = new PackageFeature(amazon, fireTV);
        PackageFeature a4 = new PackageFeature(safety, adaptiveCruise);
        //Add Models to database
        Model pacifica = new Model("Pacifica", 2022);
        Model pacificaHybrid21 = new Model("Pacifica Hybrid", 2022);
        Model pacificaHybrid22 = new Model("Pacifica Hybrid", 2021);
        //Add Trims to database




        
        em.persist(hybridEngine);
        em.persist(powerDoors);
        em.persist(handsFreeDoors);
        em.persist(fireTV);
        em.persist(rearScreens);
        em.persist(awd);
        em.persist(adaptiveCruise);
        em.persist(theater);
        em.persist(amazon);

        em.persist(safety);
        em.persist(asdf);

    
        em.getTransaction().commit();
        //Finish adding features to database
    }   

    /*
     * public double stickerPrice(){
     * Returns a Set of all Feature objects that this Automobile has
     * because of its Model, Trim, or chosen Packages. The HashSet class
     * and its addAll method will be helpful here.
     * return 0;
     * }
     * public Set<Feature> getFeatures(){
     * Returns the "sticker price" of the automobile: the sum of the Trim
     * cost, plus the costs of all packages added to the automobile. Do not
     * try to write a JPQL query to do this; you have all the data you need
     * among the fields of the Automobile object and the methods of its related
     * classes.
     * }
     */

     /*
    public void menu(){
        Scanner input = new Scanner(System.in)
        System.out.println(
            "Please choosen an option: \n"+
            "1) Instantiate model\n"+
            "2) Automobile lookup\n"+
            "3) Feature Search\n"
        );
        switch(input.nextInt()){
            case 1:
                instantiateModel();
            case 2: 
                automobileLookup();
            case 3: 
                featureSearch();
            default: 
                System.out.println("Invalid choice.");
                break;
            }
        }
    public void instantiateModel(){
    }
    public void automobileLookup(){
        //DONT FORGET TO SANITIZE INPUTS!
    }
    public void featureSearch(){
    }
        */

}
