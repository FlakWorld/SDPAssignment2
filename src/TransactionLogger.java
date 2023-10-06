import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TransactionLogger {
    private static TransactionLogger instance;
    private BufferedWriter writer;

    private TransactionLogger() {
        try {
            writer = new BufferedWriter(new FileWriter("transactions.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TransactionLogger getInstance() {
        if (instance == null) {
            instance = new TransactionLogger();
        }
        return instance;
    }

    public void logTransaction(String transaction) throws IOException {
        try {
            writer.write(transaction);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
