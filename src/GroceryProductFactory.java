//Factory Method
public class GroceryProductFactory extends ProductFactory{
    @Override
    public Product createProduct(String id, String name, int quantity, double price, double totalPrice) {
        return new GroceryProduct(id,name,quantity,price,totalPrice);
    }
}
