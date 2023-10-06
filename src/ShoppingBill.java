import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

public class ShoppingBill {
    public static void main(String[] args) throws IOException {
        Shop shop = Shop.getInstance();
        Scanner sc = new Scanner(System.in);
        char choice = '\0';
        boolean isSelfService = false;
        String cashierName = "";

        System.out.println("\t\t\t\t--------------------Invoice-----------------");
        System.out.println("\t\t\t\t\t " + "  " + "Metro Mart Grocery Shop");
        System.out.println("\t\t\t\t\t3/98 Mecrobertganj New Mumbai");
        System.out.println("\t\t\t\t\t" + "    " + "Opposite Metro Walk");
        System.out.println("GSTIN: 03AWBPP8756K592" + "\t\t\t\t\t\t\tContact: (+91) 9988776655");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        System.out.println("Date: " + formatter.format(date) + "  " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1] + "\t\t\t\t\t\t (+91) 9998887770");

        List<Product> products = new ArrayList<>();
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        System.out.println("1. Cashier Mode");
        System.out.println("2. Self-Service Mode");
        System.out.print("Choose the mode:");
        int modeChoice = sc.nextInt();


        if (modeChoice == 2) {
            isSelfService = true;
            cashierName = "SelfService";
            sc.nextLine();
        } else {
            isSelfService = false;
            sc.nextLine();
            System.out.print("Enter Cashier's Name: ");
            cashierName = sc.nextLine();
        }

        do {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Clear Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Finish Shopping");
            System.out.print("Choose an option:");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    // Add Products
                    // Read input values
                    System.out.println("Enter the product details:");
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Product Name:");
                    String name = sc.nextLine();
                    System.out.print("Quantity:");
                    int quantity = sc.nextInt();
                    System.out.print("Price (per unit):");
                    double price = sc.nextDouble();

                    // Ask for product category
                    System.out.println("Select product category:");
                    System.out.println("1. Grocery");
                    System.out.println("2. Appliance");
                    int categoryChoice = sc.nextInt();

                    // Use our FactoryMethod
                    ProductFactory factory;
                    if (categoryChoice == 1) {
                        factory = new GroceryProductFactory();
                    } else if (categoryChoice == 2){
                        factory = new ApplianceProductFactory();
                    } else {
                        System.out.println("Error: Incorrect choice. By default, choose Grocery");
                        factory = new GroceryProductFactory();
                    }

                    // Create the product and add it to the shop
                    double totalPrice = price * quantity;
                    Product product = factory.createProduct(id, name, quantity, price, totalPrice);
                    products.add(product);
                    shop.addProduct(product);

                    // Add the product to the shopping cart
                    shoppingCart.addProduct(product);

                    break;
                case "2":
                    // Delete product
                    System.out.print("Enter Product ID to remove: ");
                    String productIdToRemove = sc.nextLine();
                    shoppingCart.removeProductById(productIdToRemove);
                    break;
                case "3":
                    // Clear cart
                    shoppingCart.clear();
                    break;
                case "4":
                    //display Products
                    shoppingCart.displayProducts();
                    break;
                case "5":
                    // End of buying
                    choice = 'n';
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        } while (choice != 'n');
        Invoice currentInvoice = new Invoice(cashierName, shoppingCart);
        String invoiceData = currentInvoice.generateInvoiceData();
        System.out.println(invoiceData);
        TransactionLogger transactionLogger = TransactionLogger.getInstance();
        transactionLogger.logTransaction(invoiceData);
    }
}
