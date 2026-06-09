package br.com.crv.numtowords.tests;

import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.factory.WrittenAmountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FactoryTest {

    private WrittenAmountFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new WrittenAmountFactory();
    }

    @Test
    @DisplayName("Deve lançar exceção quando Locale não for suportado (Italiano)")
    public void testItalianLocaleUnavailable() {
        Locale locale = new Locale("it", "IT");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> factory.createWrittenAmount(locale));
        assertEquals("Implementation not available for locale: " + locale, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando Locale default não for suportado (Italiano)")
    public void testDefaultLocaleUnavailable() {
        Locale.setDefault(Locale.ITALIAN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> factory.createWrittenAmount());
        assertEquals("Implementation not available for locale: " + Locale.getDefault(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve converter valor para extenso no Locale Default (Português)")
    public void testDefaultLocale() {
        Locale.setDefault(new Locale("pt", "BR"));

        AbstractWrittenAmount wa = factory.createWrittenAmount();
        wa.setValue(BigDecimal.valueOf(1258.05));

        String expected = "Mil Duzentos e Cinquenta e Oito Reais e Cinco Centavos";
        assertEquals(expected, wa.convertToWords());
    }

    @Test
    @DisplayName("Deve converter valor para extenso no Locale Português")
    public void testPortugueseLocale() {
        AbstractWrittenAmount wa = factory.createWrittenAmount(new Locale("pt"));
        wa.setValue(BigDecimal.valueOf(2_000_100_500_000.00));

        String expected = "Dois Trilhões, Cem Milhões e Quinhentos Mil Reais";
        assertEquals(expected, wa.convertToWords());
    }

    @Test
    @DisplayName("Deve converter valor para extenso no Locale Inglês")
    public void testEnglishLocale() {
        AbstractWrittenAmount wa = factory.createWrittenAmount(Locale.ENGLISH);
        wa.setValue(BigDecimal.valueOf(1258.05));

        String expected = "One Thousand, Two Hundred Fifty-Eight Dollars and Five Cents";
        assertEquals(expected, wa.convertToWords());
    }

    @Test
    @DisplayName("Deve converter valor para extenso no Locale Espanhol")
    public void testSpanishLocale() {
        AbstractWrittenAmount wa = factory.createWrittenAmount(new Locale("es"));
        wa.setValue(BigDecimal.valueOf(1258.05));

        String expected = "Mil Doscientos Cincuenta y Ocho Pesos con Cinco Centavos";
        assertEquals(expected, wa.convertToWords());
    }

}
