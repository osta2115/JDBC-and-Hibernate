package pl.sda.introduction.hibernate;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class HibernateMain {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("h2testdb");
        entityManager = entityManagerFactory.createEntityManager();

        addProducts();

//        TypedQuery<Product> productTypedQuery = entityManager.createQuery("select p from Product p", Product.class);
//        List<Product> resultList = productTypedQuery.getResultList();
//        log.info("My products: {}", resultList);

        String sql = """
                select new pl.sda.introduction.hibernate.ProductStats(
                max(p.price), 
                min(p.price), 
                avg(p.price), 
                sum(p.price))
                from Product p""";
        var query = entityManager.createQuery(sql, ProductStats.class);
        ProductStats singleResult = query.getSingleResult();

        log.info("Max: {}", singleResult.getMax());
        log.info("Min: {}", singleResult.getMin());
        log.info("Avg: {}", singleResult.getAvg());
        log.info("Sum: {}", singleResult.getSum());

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void addProducts() {
        entityManager.getTransaction().begin();
        createProduct("Chips", BigDecimal.valueOf(5.99));
        createProduct("Fish", BigDecimal.valueOf(10.00));
        createProduct("Potatoes", BigDecimal.valueOf(3.00));
        createProduct("Tomatoes", BigDecimal.valueOf(6.67));
        createProduct("Chicken", BigDecimal.valueOf(15.25));
        createProduct("Pizza", BigDecimal.valueOf(20.00));
        entityManager.getTransaction().commit();
    }

    private static void createProduct(String description, BigDecimal price) {
        var product = new Product();
        product.setProductDescription(description);
        product.setPrice(price);
        entityManager.persist(product);
    }
}
