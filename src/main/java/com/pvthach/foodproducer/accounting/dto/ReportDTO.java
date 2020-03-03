package com.pvthach.foodproducer.accounting.dto;


import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class ReportDTO implements Serializable {

    private Long totalIncome;

    private Long totalExpense;

    private List<String> incomeLabels;

    private List<Long> incomeData;

    private List<String> expenseLabels;

    private List<Long> expenseData;


    public ReportDTO() {
    }

    public Long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Long getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Long totalExpense) {
        this.totalExpense = totalExpense;
    }

    public List<String> getIncomeLabels() {
        return incomeLabels;
    }

    public void setIncomeLabels(List<String> incomeLabels) {
        this.incomeLabels = incomeLabels;
    }

    public List<Long> getIncomeData() {
        return incomeData;
    }

    public void setIncomeData(List<Long> incomeData) {
        this.incomeData = incomeData;
    }

    public List<String> getExpenseLabels() {
        return expenseLabels;
    }

    public void setExpenseLabels(List<String> expenseLabels) {
        this.expenseLabels = expenseLabels;
    }

    public List<Long> getExpenseData() {
        return expenseData;
    }

    public void setExpenseData(List<Long> expenseData) {
        this.expenseData = expenseData;
    }
}