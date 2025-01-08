package entity.report;

import entity.transaction.AbstractTransaction;

import java.util.List;

public class FinancialReport implements IReport {
    private String startDate;
    private String endDate;
    private List<AbstractTransaction> transactions;

    @Override
    public String generateReport() {
        return "";
        // TODO
    }
}
