package br.com.currencyconverter;

public class CurrencyOptionHandler {
    private static String baseCurrency;
    private static String targetCurrency;

    public static void setCurrencyOption(int option) {
        switch (option) {
            case 1:
                baseCurrency = "USD";
                targetCurrency = "ARS";
                break;
            case 2:
                baseCurrency = "ARS";
                targetCurrency = "USD";
                break;
            case 3:
                baseCurrency = "USD";
                targetCurrency = "BRL";
                break;
            case 4:
                baseCurrency = "BRL";
                targetCurrency = "USD";
                break;
            case 5:
                baseCurrency = "USD";
                targetCurrency = "COP";
                break;
            case 6:
                baseCurrency = "COP";
                targetCurrency = "USD";
                break;
            default:
                throw new IllegalArgumentException("Opção inválida");
        }
    }

    public static String getBaseCurrency() {
        return baseCurrency;
    }

    public static String getTargetCurrency() {
        return targetCurrency;
    }
}
