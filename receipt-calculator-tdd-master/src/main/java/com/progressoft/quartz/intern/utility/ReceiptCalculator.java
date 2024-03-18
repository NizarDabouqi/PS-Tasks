package com.progressoft.quartz.intern.utility;

import java.util.ArrayList;

public interface ReceiptCalculator {

    Receipt calculateReceipt(ArrayList<Meal> meals, double tax, int numberOfCustomers);
}
