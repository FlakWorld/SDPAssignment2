import java.util.ArrayList;
import java.util.List;
//Singleton
public class ShoppingCart {
    private static ShoppingCart instance;
    private List<Product> products;

    public static ShoppingCart getInstance() {
        if (instance==null) {
            instance = new ShoppingCart();
        }
        return instance;
    }
    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProductById(String productId) {
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            products.remove(productToRemove);
        } else {
            System.out.println("Product with ID " + productId + " not found in the shopping cart.");
        }
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getTotalPrice();
        }
        return totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("The shopping cart is empty.");
        } else {
            System.out.println("Products in the shopping cart:");
            for (Product product : products) {
                System.out.println("ID: " + product.getId() +
                        ", Name: " + product.getName() +
                        ", Quantity: " + product.getQuantity() +
                        ", Price per Unit: " + product.getPrice() +
                        ", Total Price: " + product.getTotalPrice());
            }
            System.out.println();
        }
    }
}
