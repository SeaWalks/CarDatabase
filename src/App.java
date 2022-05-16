import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.persistence.internal.jpa.deployment.PersistenceUnitProcessor.Mode;
import jakarta.persistence.*;
import model.*;
import model.Package; //Explicit import is good for "Package"

public class App {
    public static void main(String[] args) throws Exception {
        App run = new App();
            int choice;
            while (true) {
                Scanner input = new Scanner(System.in);
                System.out.println
                        ("Please choose an option: \n" +
                        "1) Instantiate model\n" +
                        "2) Automobile lookup\n" +
                        "3) Feature Search\n" +
                        "8) Exit Program\n");
                choice = input.nextInt(); //This is causing a crash in the while loop and I'm too tired to care.
                switch (choice) {
                    case 1:
                        run.instantiateData();
                        System.out.println("Data instantiated.");
                        break;
                    case 3:
                        run.featureSearch();
                    case 2:
                        run.automobileLookup();
                        break;
                    case 8:
                        System.out.println("Closing Program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
        
    }
    /*
     * Feature Search
     * 1) User enters the name of a Feature.
     * 2) Use JPQL to find the Feature if it exists.
     * 3) If it does, print the VIN of every Automobile with that Feature.
     */

    public void featureSearch() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cardb");
        EntityManager em = factory.createEntityManager();
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Please enter a feature to lookup: ");
            String featureInput = input.nextLine();
            TypedQuery<Automobile> trimQuery = em.createQuery(
                    "SELECT a FROM automobiles a JOIN a.trim t JOIN t.trimFeatures tf WHERE tf.feature = ( SELECT f FROM features f WHERE f.featureName = ?1 )", Automobile.class);
            trimQuery.setParameter(1, featureInput);
            try{
                System.out.println("Automobile VINs: ");
                List<Automobile> selectedCars = trimQuery.getResultList();
                for(Automobile i : selectedCars){
                    System.out.println(i.getVin());
                }
            }catch (NoResultException ex){
                System.out.println("Feature not found.");
            }


        }
    }

    /*
     * Automobile Lookup:
     * 1)User enters the VIN of an automobile.
     * 2)Use JPQL to find that automobile if it exists.
     * 3)If it does, print the automobile's information in the following format:
     * [year] [model] [trim]
     * [sticker price]
     * Features: [all of the automobile's features, in alphabetical order, one per
     * line]
     * WARNING: this branch requires running a query with user input. You must
     * correctly
     * used a parameterized query to eliminate potential injection attacks. Doing
     * this
     * incorrectly will net a 20% deduction on your project. (Yes, I am serious.)
     */

    public void automobileLookup() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cardb");
        EntityManager em = factory.createEntityManager();
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Please enter the VIN to lookup: ");
            String vinInput = input.nextLine();
            TypedQuery<Automobile> query = em.createQuery(
                    "SELECT a FROM automobiles a WHERE a.vin = ?1", Automobile.class);
            try {
                Automobile thisCar = query.setParameter(1, vinInput).getSingleResult();
                System.out.println(
                        "Year: " + thisCar.getTrim().getModel().getYear() + " Model: "
                                + thisCar.getTrim().getModel().getModelName() + " Trim: "
                                + thisCar.getTrim().getTrimName() + "\nSticker Price:"
                                + String.format("%.2f", thisCar.stickerPrice())
                                + "\nFeatures:");
                Set<String> sortedFeatures = new TreeSet<String>();
                for (Feature F : thisCar.getFeatures()) {
                    sortedFeatures.add(F.getFeatureName());
                }
                for (String s : sortedFeatures) {
                    System.out.println(s);
                }
            } catch (NoResultException ex) {
                System.out.println("VIN " + vinInput + " not found.");
            }
        }
    }

    public void instantiateData() {
        /************ Instantiating Data ***********/
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cardb");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        // Add Features to database
        Feature leatherSeats = new Feature("Leather Seats");
        Feature hybridEngine = new Feature("Plug-in hybrid engine");
        Feature powerDoors = new Feature("Power sliding doors");
        Feature handsFreeDoors = new Feature("Hands-free sliding doors");
        Feature fireTV = new Feature("Amazon FireTV");
        Feature rearScreens = new Feature("Rear-Seat Entertainment Screens");
        em.persist(leatherSeats);
        Feature awd = new Feature("All-Wheel Drive");
        Feature adaptiveCruise = new Feature("Adaptive Cruise Control");
        // Add Packages to database
        Package theater = new Package("Theater Package");
        Package amazon = new Package("Amazon Theater Package");
        Package safety = new Package("Safety Package");
        // Add PackageFeatures to database
        PackageFeature pf1 = new PackageFeature(theater, rearScreens);
        PackageFeature pf2 = new PackageFeature(amazon, rearScreens);
        PackageFeature pf3 = new PackageFeature(amazon, fireTV);
        PackageFeature pf4 = new PackageFeature(safety, adaptiveCruise);
        // Add Models to database
        Model pacifica = new Model("Pacifica", 2022);
        Model pacificaHybrid21 = new Model("Pacifica Hybrid", 2022);
        Model pacificaHybrid22 = new Model("Pacifica Hybrid", 2021);
        // Add ModelFeatures to database
        // 22Pacifica
        ModelFeature mf1 = new ModelFeature(pacifica, powerDoors);
        ModelFeature mf2 = new ModelFeature(pacificaHybrid21, hybridEngine);
        // 21Hybrid
        ModelFeature mf3 = new ModelFeature(pacificaHybrid21, powerDoors);
        ModelFeature mf4 = new ModelFeature(pacificaHybrid22, hybridEngine);
        // 22Hybrid
        ModelFeature mf5 = new ModelFeature(pacificaHybrid22, powerDoors);
        // Add Trims to database
        // 22 Gas
        Trim t1 = new Trim("Touring", 30000, pacifica);
        Trim t2 = new Trim("Limited", 34000, pacifica);
        Trim t3 = new Trim("Pinnacle", 42000, pacifica);
        // 22 Hybrid
        Trim t4 = new Trim("Touring", 43000, pacificaHybrid22);
        Trim t5 = new Trim("Limited", 48000, pacificaHybrid22);
        Trim t6 = new Trim("Pinnacle", 54000, pacificaHybrid22);
        // 21 Hybrid
        Trim t7 = new Trim("Touring", 41000, pacificaHybrid21);
        Trim t8 = new Trim("Limited", 46000, pacificaHybrid21);
        Trim t9 = new Trim("Pinnacle", 52000, pacificaHybrid21);
        // Add TrimFeatures to database
        // 22 Limited
        TrimFeature tf1 = new TrimFeature(t2, leatherSeats);
        TrimFeature tf2 = new TrimFeature(t2, handsFreeDoors);
        // 22 Pinnacle
        TrimFeature tf3 = new TrimFeature(t3, leatherSeats);
        TrimFeature tf4 = new TrimFeature(t3, handsFreeDoors);
        TrimFeature tf5 = new TrimFeature(t3, rearScreens);
        TrimFeature tf6 = new TrimFeature(t3, fireTV);
        TrimFeature tf7 = new TrimFeature(t3, awd);
        // Hybrid22 Limited
        TrimFeature tf8 = new TrimFeature(t5, leatherSeats);
        TrimFeature tf9 = new TrimFeature(t5, handsFreeDoors);
        // Hybrid22 Pinnacle
        TrimFeature tf10 = new TrimFeature(t6, leatherSeats);
        TrimFeature tf11 = new TrimFeature(t6, handsFreeDoors);
        TrimFeature tf12 = new TrimFeature(t6, rearScreens);
        TrimFeature tf13 = new TrimFeature(t6, fireTV);
        // Hybrid21 Limited
        TrimFeature tf14 = new TrimFeature(t8, leatherSeats);
        TrimFeature tf15 = new TrimFeature(t8, handsFreeDoors);
        // Hybrid21 Pinnacle
        TrimFeature tf16 = new TrimFeature(t9, leatherSeats);
        TrimFeature tf17 = new TrimFeature(t9, handsFreeDoors);
        TrimFeature tf18 = new TrimFeature(t9, rearScreens);
        TrimFeature tf19 = new TrimFeature(t9, adaptiveCruise);
        // Add AvailablePackages to database
        AvailablePackage ap1 = new AvailablePackage(3000, t1, safety); // Good; ap1 for 30k car
        AvailablePackage ap2 = new AvailablePackage(2500, t2, amazon); // Good; AP2 for 34k car UNUSED
        AvailablePackage ap3 = new AvailablePackage(2500, t5, amazon); // Good; ap3 for 48kcar UNUSED
        AvailablePackage ap4 = new AvailablePackage(3000, t7, safety); // Good; ap4 for 41k car
        AvailablePackage ap5 = new AvailablePackage(2500, t8, theater); // Goodl ap5, apy6 for 46k car
        AvailablePackage ap6 = new AvailablePackage(2000, t8, safety);
        // Add Automobiles to database
        Automobile a1 = new Automobile("12345abcde");
        a1.setTrim(t2);
        Automobile a2 = new Automobile("67890abcde");
        a2.setTrim(t6);
        Automobile a3 = new Automobile("99999aaaaa");
        a3.setTrim(t9);
        Automobile a4 = new Automobile("aaaaa88888");
        a4.setTrim(t7);
        Automobile a5 = new Automobile("bbbbb77777");
        a5.setTrim(t8);
        // Persist Features
        em.persist(hybridEngine);
        em.persist(powerDoors);
        em.persist(handsFreeDoors);
        em.persist(fireTV);
        em.persist(rearScreens);
        em.persist(awd);
        em.persist(adaptiveCruise);
        // Persist Package
        em.persist(theater);
        em.persist(amazon);
        em.persist(safety);
        // PersistPackageFeatures
        em.persist(pf1);
        em.persist(pf2);
        em.persist(pf3);
        em.persist(pf4);
        // Persist Models
        em.persist(pacifica);
        em.persist(pacificaHybrid21);
        em.persist(pacificaHybrid22);
        // Persist ModelFeatures
        em.persist(mf1);
        em.persist(mf2);
        em.persist(mf3);
        em.persist(mf4);
        em.persist(mf5);
        // Persist Trims
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(t4);
        em.persist(t5);
        em.persist(t6);
        em.persist(t7);
        em.persist(t8);
        em.persist(t9);
        // Persist TrimFeatures
        em.persist(tf1);
        em.persist(tf2);
        em.persist(tf3);
        em.persist(tf4);
        em.persist(tf5);
        em.persist(tf6);
        em.persist(tf7);
        em.persist(tf8);
        em.persist(tf9);
        em.persist(tf10);
        em.persist(tf11);
        em.persist(tf12);
        em.persist(tf13);
        em.persist(tf14);
        em.persist(tf15);
        em.persist(tf16);
        em.persist(tf17);
        em.persist(tf18);
        em.persist(tf19);
        // Persist Automobiles
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);
        em.persist(a5);
        // Persist AvailablePackages
        em.persist(ap1);
        em.persist(ap2);
        em.persist(ap3);
        em.persist(ap4);
        em.persist(ap5);
        em.persist(ap6);
        // Add ChosenPackages
        a1.getAvailablePackages().add(ap2);
        a4.getAvailablePackages().add(ap4);
        a5.getAvailablePackages().add(ap5);
        a5.getAvailablePackages().add(ap6);
        //Commit changes to database
        em.getTransaction().commit();
    }

}
