package br.com.currencyconverter;

import java.text.NumberFormat;
import java.util.Currency;

public class CurrencyFormat {
    private static Currency localeCurrency;
    private static NumberFormat formatCurrency = NumberFormat.getCurrencyInstance();

    public static String total(double total) {
        localeCurrency = Currency.getInstance(CurrencyOptionHandler.getTargetCurrency());
        return  format(total, localeCurrency);
    }

    public static String amount(double amount) {
        localeCurrency = Currency.getInstance(CurrencyOptionHandler.getBaseCurrency());
        return format(amount, localeCurrency);
    }

    private static String format(double value, Currency currency) {
        formatCurrency.setCurrency(currency);
        return formatCurrency.format(value);
    }
}
