package pl.sda.library.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LibraryMain {

    public static void main(String[] args) {
        var entityManagerFactory = Persistence.createEntityManagerFactory("mysql-library");
        var entityManager = entityManagerFactory.createEntityManager();



        entityManager.close();
        entityManagerFactory.close();
    }
}
