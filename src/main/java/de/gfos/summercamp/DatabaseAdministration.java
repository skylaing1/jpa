package de.gfos.summercamp;

import de.gfos.summercamp.entity.Mitarbeiter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseAdministration {

    public static Scanner myObj = new Scanner(System.in);

    private static List<Mitarbeiter> Data = new ArrayList<>();


    public static void run() {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa")) {
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                entityManager.getTransaction().begin();

                menu(entityManager);


                entityManager.getTransaction().commit();
            }
        }
    }


    public static void menu(EntityManager entityManager) {
        while (true) {
            System.out.println("\n1: Hinzufuegen | 2: Loeschen | 3: Bearbeiten | 4: Ausgeben | 5: Programm Beenden");
            System.out.print("Eingabe: ");
            myObj = new Scanner(System.in);
            int menuInput = myObj.nextInt();
            if (menuInput == 2) {
                System.out.println(deleteData(entityManager));
            } else if (menuInput == 1) {
                System.out.println(addData(entityManager));
            } else if (menuInput == 3) {
                editRecord(entityManager);
            } else if (menuInput == 4) {
                System.out.println("\n1: Ein Datensatz | 2: Alle Datensaetze");
                System.out.print("Eingabe: ");
                menuInput = myObj.nextInt();
                if (menuInput == 1) {
                    System.out.println("\nGeben sie die ID des benötigten Datensatzes ein:");
                    System.out.print("Eingabe: ");
                    long index = myObj.nextLong();
                    System.out.println(readOneRecord(entityManager, index));
                }
                if (menuInput == 2) {
                    System.out.println(readAllRecords(entityManager, "",""));
                }
            } else if (menuInput == 5) {
                System.out.println("Programm Wird Beendet");
            }
        }
    }


    public static Mitarbeiter deleteData(EntityManager entityManager) {
        System.out.println("\nGeben sie die ID der zu löschenden Person ein:");
        System.out.print("Eingabe: ");
        long ID = myObj.nextLong();

        Mitarbeiter current = readOneRecord(entityManager, ID);
        entityManager.remove(current);
        return current;
    }

    public static Mitarbeiter addData(EntityManager entityManager) {
        System.out.println("Nachname?");
        String lastname = myObj.nextLine();
        System.out.println("Vorname?");
        String firstname = myObj.next();
        System.out.println("Alter?");
        int age = myObj.nextInt();
        System.out.println("Gehalt?");
        double salary = myObj.nextDouble();
        Mitarbeiter current = new Mitarbeiter(lastname, firstname, age, salary);
        entityManager.persist(current);


        return current;
    }

    public static List<Mitarbeiter> readAllRecords(EntityManager entityManager, String parameter, String wert) {
        if (parameter.isBlank() || wert.isBlank()) {
            return entityManager.createQuery("SELECT e from Mitarbeiter e", Mitarbeiter.class).getResultList();
        } else {
            return entityManager.createQuery("select e from Mitarbeiter e where " + parameter + " = '" + wert + "'", Mitarbeiter.class).getResultList();
        }
    }


    public static Mitarbeiter readOneRecord(EntityManager entityManager, long index) {

        return entityManager.find(Mitarbeiter.class, index);
    }

    public static void editRecord(EntityManager entityManager) {
        readAllRecords(entityManager,"","");
        System.out.println("\nGeben sie das Attribut ein nach welchem gefiltert werden soll");
        System.out.print("Eingabe: ");
        myObj = new Scanner(System.in);
        String parameter = myObj.nextLine();
        System.out.println("\nGeben sie den Wert des Attributes ein:");
        System.out.print("Eingabe: ");
        myObj = new Scanner(System.in);
        String wert = myObj.nextLine();
       System.out.println(readAllRecords(entityManager,parameter,wert));
    }

}
