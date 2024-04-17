package br.com.currencyconverter;

import java.io.IOException;

public class CurrencyConverterApp {
    private final Menu menu;
    private final CurrencyApiService currencyApiService;

    public CurrencyConverterApp() {
        this.menu = new Menu();
        this.currencyApiService = new CurrencyApiService();
    }

    public void run() {
        while (true) {
            menu.display();
            var option = menu.requestMenuOption();
            var shouldStop = menu.shouldStop(option);
            if(shouldStop) break;
            var amount = menu.requestAmountToConvert();
            CurrencyOptionHandler.setCurrencyOption(option);

            try {
                var exchangeRate = currencyApiService.getExchangeRate();
                var total = amount * Double.parseDouble(String.valueOf(exchangeRate));
                var totalFormated = CurrencyFormat.total(total);
                var amountFormated = CurrencyFormat.amount(amount);
                menu.displayResult(amountFormated, totalFormated);
            } catch (IOException | InterruptedException e) {
                menu.displayServerError();
            }
        }
    }
}
