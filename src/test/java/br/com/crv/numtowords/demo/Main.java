package br.com.crv.numtowords.demo;

import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.factory.WrittenAmountFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Author: Celso R. Vitorino (github.com/celsorv)
 * Created: December 2025
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        scanner.useLocale(Locale.US); // Use dot (.) as the decimal separator

        // Português (Brasil)
        // Locale locale = Locale.forLanguageTag("pt-BR");

        // Espanhol (México)
        // Locale locale = Locale.forLanguageTag("es-MX");

        // Inglês (EUA)
        Locale locale = Locale.forLanguageTag("en-US");

        // Inglês Britânico
        // Locale locale = Locale.forLanguageTag("en-GB");

        // Francês
        // Locale locale = Locale.forLanguageTag("fr-FR");

        DecimalFormat df = new DecimalFormat("$ #,##0.00");

        WrittenAmountFactory factory = new WrittenAmountFactory();
        AbstractWrittenAmount wa = factory.createWrittenAmount(locale);

        while (true) {
            try {
                System.out.print("\nDigite um valor (ou 0 para sair): ");
                BigDecimal valor = scanner.nextBigDecimal();

                if (valor.compareTo(BigDecimal.ZERO) == 0) {
                    System.out.println("Encerrando o programa...");
                    break;
                }

                String valorFormatado = df.format(valor);
                System.out.println("Valor formatado: " + valorFormatado);

                wa.setValue(valor);
                String extenso = wa.convertToWords();
                System.out.println("Extenso: " + extenso);

            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Use ponto para decimais.");
                scanner.nextLine(); // Limpa a entrada inválida do buffer
            }
        }

        scanner.close();
    }
}