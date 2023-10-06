import java.util.*;
//Singleton pattern
public class Shop {
    private static Shop instance;
    private List<Product> products;
    private Shop() {
        products = new ArrayList<>();
    }
    public static Shop getInstance() {
        if (instance==null) {
            instance = new Shop();
        }
        return instance;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
}
