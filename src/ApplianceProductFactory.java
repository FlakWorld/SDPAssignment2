//Factory Method
public class ApplianceProductFactory extends ProductFactory{
    @Override
    public Product createProduct(String id, String name, int quantity, double price, double totalPrice) {
        return new ApplianceProduct(id,name,quantity,price,totalPrice);
    }
}
