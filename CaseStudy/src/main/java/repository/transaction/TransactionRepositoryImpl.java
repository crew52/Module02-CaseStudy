package repository.transaction;

import entity.account.Account;
import entity.transaction.AbstractTransaction;
import entity.transaction.DepositTransaction;
import entity.transaction.TransferTransaction;
import entity.transaction.WithdrawTransaction;
import utility.FileManagerImpl;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionRepositoryImpl implements TransactionRepository {

    // Cố định tên file
    private static final String FILE_NAME = "transactions.dat"; // Đặt tên file cố định ở đây
    private final FileManagerImpl<AbstractTransaction> fileManager;

    public TransactionRepositoryImpl() {
        this.fileManager = new FileManagerImpl<>();
    }

    @Override
    public void writeTransactionsToFile(List<AbstractTransaction> transactions) {
        fileManager.writeToFile(transactions, FILE_NAME); // Sử dụng FILE_NAME thay vì truyền tên file
    }

    @Override
    public List<AbstractTransaction> readTransactionsFromFile() {
        return fileManager.readFromFile(FILE_NAME); // Sử dụng FILE_NAME thay vì truyền tên file
    }

    @Override
    public AbstractTransaction findTransactionById(String transactionId) {
        List<AbstractTransaction> transactions = fileManager.readFromFile(FILE_NAME);
        if (transactions != null) {
            for (AbstractTransaction transaction : transactions) {
                if (transaction.getTransactionId().equals(transactionId)) {
                    return transaction;
                }
            }
        }
        return null; // Không tìm thấy giao dịch theo ID
    }

    @Override
    public List<AbstractTransaction> findTransactionsByAccount(Account account) {
        List<AbstractTransaction> transactions = fileManager.readFromFile(FILE_NAME);
        if (transactions != null) {
            return transactions.stream()
                    .filter(transaction -> transaction.toString().contains(account.getAccountId()))
                    .collect(Collectors.toList());
        }
        return null; // Không tìm thấy giao dịch theo tài khoản
    }

    @Override
    public List<TransferTransaction> findTransferTransactions() {
        List<AbstractTransaction> transactions = fileManager.readFromFile(FILE_NAME);
        if (transactions != null) {
            return transactions.stream()
                    .filter(transaction -> transaction instanceof TransferTransaction)
                    .map(transaction -> (TransferTransaction) transaction)
                    .collect(Collectors.toList());
        }
        return null; // Không tìm thấy giao dịch chuyển khoản
    }

    @Override
    public List<DepositTransaction> findDepositTransactions() {
        List<AbstractTransaction> transactions = fileManager.readFromFile(FILE_NAME);
        if (transactions != null) {
            return transactions.stream()
                    .filter(transaction -> transaction instanceof DepositTransaction)
                    .map(transaction -> (DepositTransaction) transaction)
                    .collect(Collectors.toList());
        }
        return null; // Không tìm thấy giao dịch nạp tiền
    }

    @Override
    public List<WithdrawTransaction> findWithdrawTransactions() {
        List<AbstractTransaction> transactions = fileManager.readFromFile(FILE_NAME);
        if (transactions != null) {
            return transactions.stream()
                    .filter(transaction -> transaction instanceof WithdrawTransaction)
                    .map(transaction -> (WithdrawTransaction) transaction)
                    .collect(Collectors.toList());
        }
        return null; // Không tìm thấy giao dịch rút tiền
    }
}