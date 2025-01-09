package utility;

import designPattern.TransactionServiceSingleton;
import entity.transaction.AbstractTransaction;

public class TransactionUtil {
    // Phương thức thêm giao dịch vào hệ thống chung
    public static void addTransactionToSystem(AbstractTransaction transaction) {
        TransactionService transactionService = TransactionServiceSingleton.getInstance();
        if (transaction != null && transactionService != null) {
            transactionService.addTransaction(transaction); // Thêm giao dịch vào hệ thống chung
        }
    }
}
