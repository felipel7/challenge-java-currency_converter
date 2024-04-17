package br.com.currencyconverter;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        System.out.println("""
                ********************************************************
                Seja bem-vindo/a ao Conversor de moeda =]

                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileiro
                4) Real brasileiro =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Sair
                Escolha uma opção válida:
                ********************************************************
                """);
    }

    public void displayResult(String amountFormated, String totalFormated) {
        System.out.printf("""
                Valor %s [%s] corresponde ao valor final de => %s [%s] \n
                """, amountFormated, CurrencyOptionHandler.getBaseCurrency(), totalFormated, CurrencyOptionHandler.getTargetCurrency()
        );
        System.out.println("Pressione \"Enter\" para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int requestMenuOption() {
        while (true) {
            var option = scanner.next();
            if (isValidOption(option)) return Integer.parseInt(option);

            showInvalidOption();
        }
    }

    public double requestAmountToConvert() {
        System.out.print("Digite o valor que deseja converter: ");

        while (true) {
            var amount = scanner.next();
            if (isValidAmount(amount)) return Double.parseDouble(amount);

            showInvalidValue();
        }
    }

    public boolean shouldStop(int option) {
        return option == 7;
    }

    private boolean isValidAmount(String amount) {
        try {
            double totalAmount = Double.parseDouble(amount);
            return totalAmount >= 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidOption(String option) {
        try {
            int numericOption = Integer.parseInt(option);
            return numericOption >= 1 && numericOption <= 7;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showInvalidOption() {
        System.out.print("Opção inválida, tente novamente: ");
    }

    private void showInvalidValue() {
        System.out.print("Valor inválido, digite um valor positivo: ");
    }

    public void displayServerError() {
        System.out.print("Ocorreu um erro interno. Por favor, tente novamente.");
    }
}
