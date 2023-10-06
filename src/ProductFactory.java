//Abstract Factory Method
public abstract class ProductFactory {
    public abstract Product createProduct(String id, String name, int quantity, double price, double totalPrice);
}
