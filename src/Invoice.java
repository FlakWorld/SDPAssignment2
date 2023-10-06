import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Invoice {
    private String cashierName;
    private ShoppingCart shoppingCart;
    private Date date;

    public Invoice(String cashierName, ShoppingCart shoppingCart) {
        this.cashierName = cashierName;
        this.shoppingCart = shoppingCart;
        this.date = new Date();
    }

    public String generateInvoiceData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        StringBuilder invoiceData = new StringBuilder();
        invoiceData.append("\t\t\t\t--------------------Invoice-----------------\n");
        invoiceData.append("\t\t\t\t\t " + "  " + "Metro Mart Grocery Shop\n");
        invoiceData.append("\t\t\t\t\t3/98 Mecrobertganj New Mumbai\n");
        invoiceData.append("\t\t\t\t\t" + "    " + "Opposite Metro Walk\n");
        invoiceData.append("GSTIN: 03AWBPP8756K592\t\t\t\t\t\t\tContact: (+91) 9988776655\n");
        invoiceData.append("Date: " + formatter.format(date) + "  " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1] + "\t\t\t\t\t\t (+91) 9998887770\n");

        double overAllPrice = shoppingCart.calculateTotalPrice();
        double discount = overAllPrice * 2 / 100;
        double subtotal = overAllPrice - discount;
        double sgst = overAllPrice * 12 / 100;
        double cgst = overAllPrice * 12 / 100;
        double invoiceTotal = subtotal + sgst + cgst;

        invoiceData.append("\nCashier: " + cashierName + "\n");
        invoiceData.append("\nProduct ID \t\tName\t\tQuantity\t\tRate \t\t\t\tTotal Price\n");
        invoiceData.append("-----------------------------------------------------------------------------------------------------------------------------------\n");
        for (Product product : shoppingCart.getProducts()) {
            invoiceData.append(String.format("   %-9s             %-9s      %5d               %9.2f                       %14.2f\n", product.getId(),
                    product.getName(), product.getQuantity(), product.getPrice(), product.getTotalPrice()));
        }
        invoiceData.append("\nTotal Amount (Rs.) " + overAllPrice + "\n");
        invoiceData.append("\nDiscount (Rs.) " + discount + "\n");
        invoiceData.append("\nSubtotal " + subtotal + "\n");
        invoiceData.append("\nSGST (%) " + sgst + "\n");
        invoiceData.append("\nCGST (%) " + cgst + "\n");
        invoiceData.append("\nInvoice Total " + invoiceTotal + "\n");
        invoiceData.append("\t\t\t\t----------------Thank You for Shopping!!-----------------\n");
        invoiceData.append("\t\t\t\t                     Visit Again\n");

        return invoiceData.toString();
    }
}
