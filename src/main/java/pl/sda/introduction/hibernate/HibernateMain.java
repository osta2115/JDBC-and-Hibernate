package pl.sda.introduction.hibernate;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Slf4j
public class HibernateMain {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("mysql-shop");
        entityManager = entityManagerFactory.createEntityManager();


        addProductsWithDetails();
        updateProductPriceInMeatCategory();

        var query = entityManager.createQuery("select pc from ProductCategory pc ", ProductCategory.class);
        var allCategories = query.getResultList();

        allCategories.forEach(
                productCategory -> log.info("Products in category {}: {}",
                        productCategory.getName(), productCategory.getProducts()));

        var queryProductsWithDetails = entityManager.createQuery("select p from Product p", Product.class);
        List<Product> resultList = queryProductsWithDetails.getResultList();
        for (Product product : resultList) {
            log.info("Product with details: {}", product);
        }

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void updateProductPriceInMeatCategory() {
        entityManager.getTransaction().begin();

        String selectAllProductsWithMeatCategory = """
                select p from Product p
                join p.productCategory pc
                where pc.name = :category
                """;
        TypedQuery<Product> query = entityManager.createQuery(selectAllProductsWithMeatCategory, Product.class);
        query.setParameter("category", "Meat");
        var productsInMeatCategory = query.getResultStream();
        productsInMeatCategory.forEach(decreasePrice());


        entityManager.getTransaction().commit();
    }

    private static Consumer<Product> decreasePrice() {
        return product -> {
            log.info("{} - old info", product);
            product.decreasePrice(BigDecimal.valueOf(2.00));
            product.setUpdateDatetime(LocalDateTime.now());
            log.info("{} - new info", product);
        };
    }

    private static void getAllProducts() {
        TypedQuery<Product> productTypedQuery = entityManager.createQuery("select p from Product p", Product.class);
        List<Product> resultList = productTypedQuery.getResultList();
        log.info("My products: {}", resultList);
    }

    private static void filterProducts() {
        var selectProductWithSpecificPrice = """
                select p from Product p
                where p.price.priceValue > :providedPrice
                or p.productDescription like :description
                """;
        TypedQuery<Product> query = entityManager.createQuery(selectProductWithSpecificPrice, Product.class);
        query.setParameter("providedPrice", BigDecimal.valueOf(10));
        query.setParameter("description", "%" + "oes" + "%");
        Stream<Product> filteredProducts = query.getResultStream();
        filteredProducts.forEach(product -> log.info("{}", product));
    }

    private static ProductCategory createCategory(String name) {
        var productCategory = new ProductCategory();
        productCategory.setName(name);
        return productCategory;
    }

    private static void showProductStats() {
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
    }

    private static void addProducts() {
        var vegetables = createCategory("Vegetables");
        var other = createCategory("Other");
        var meat = createCategory("Meat");

        var chips = createProduct("Chips", BigDecimal.valueOf(5.99));
        chips.setProductCategory(other);

        var chicken = createProduct("Chicken", BigDecimal.valueOf(15.25));
        chicken.setProductCategory(meat);

        var pizza = createProduct("Pizza", BigDecimal.valueOf(20.00));
        pizza.setProductCategory(other);

        var fish = createProduct("Fish", BigDecimal.valueOf(10.00));
        fish.setProductCategory(meat);

        var potatoes = createProduct("Potatoes", BigDecimal.valueOf(3.00));
        potatoes.setProductCategory(vegetables);

        var tomatoes = createProduct("Tomatoes", BigDecimal.valueOf(6.67));
        tomatoes.setProductCategory(vegetables);

        entityManager.getTransaction().begin();

        entityManager.persist(chicken);
        entityManager.persist(chips);
        entityManager.persist(pizza);
        entityManager.persist(tomatoes);
        entityManager.persist(potatoes);
        entityManager.persist(fish);

        entityManager.getTransaction().commit();

    }

    private static void addProductsWithDetails() {
        var meat = createCategory("Meat");
        var chicken = createProduct("Chicken", BigDecimal.valueOf(15.25));
        chicken.setProductCategory(meat);

        var productDetailsChicken = new ProductDetails();
        productDetailsChicken.setCarbonaceous(40);
        productDetailsChicken.setFat(15);
        productDetailsChicken.setProtein(45);
        productDetailsChicken.setExpiryDate(LocalDate.of(2021,9,1));
        productDetailsChicken.setKcal(150);
        productDetailsChicken.setProduct(chicken);
        chicken.setProductDetails(productDetailsChicken);

        entityManager.getTransaction().begin();
        entityManager.persist(chicken);
        entityManager.getTransaction().commit();

    }

    private static Product createProduct(String description, BigDecimal priceValue) {
        var product = new Product();
        product.setProductDescription(description);
        var price = new Price();
        price.setPriceValue(priceValue);
        price.setCurrency("PLN");
        product.setPrice(price);
        return product;
    }
}
