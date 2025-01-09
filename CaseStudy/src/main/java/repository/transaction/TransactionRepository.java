package repository.transaction;

import entity.Customer;
import entity.account.Account;
import entity.transaction.AbstractTransaction;
import entity.transaction.DepositTransaction;
import entity.transaction.TransferTransaction;
import entity.transaction.WithdrawTransaction;

import java.util.List;

public interface TransactionRepository {
    // Ghi tất cả các giao dịch vào file
    void writeTransactionsToFile(List<AbstractTransaction> transactions);

    // Đọc các giao dịch từ file
    List<AbstractTransaction> readTransactionsFromFile();

    // Tìm kiếm giao dịch trong file theo ID
    AbstractTransaction findTransactionById(String transactionId);

    // Tìm kiếm giao dịch trong file theo tài khoản
    List<AbstractTransaction> findTransactionsByAccount(Account account);

    // Tìm kiếm các giao dịch chuyển khoản trong file
    List<TransferTransaction> findTransferTransactions();

    // Tìm kiếm các giao dịch nạp tiền trong file
    List<DepositTransaction> findDepositTransactions();

    // Tìm kiếm các giao dịch rút tiền trong file
    List<WithdrawTransaction> findWithdrawTransactions();
}
