import java.util.List;
import java.util.Scanner;
import jakarta.persistence.*;
import model.*;

public class App {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cardb");
        EntityManager em = factory.createEntityManager();
        System.out.println("Hopefully this works.");
    }
}
