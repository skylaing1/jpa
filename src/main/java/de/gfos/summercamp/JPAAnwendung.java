package de.gfos.summercamp;

import de.gfos.summercamp.entity.Mitarbeiter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAAnwendung {

    public static void main(String[] args) {
        DatabaseAdministration.run();
    }


}