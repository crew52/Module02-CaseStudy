package designPattern;

public class TransactionServiceSingleton {
    private static TransactionService instance;

    private TransactionServiceSingleton() {}

    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionServiceImpl(); // Hoặc bất kỳ implementation nào của TransactionService
        }
        return instance;
    }
}
