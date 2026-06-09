package br.com.crv.numtowords.tests;

import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.es.SpanishNumberDefinitions;
import br.com.crv.numtowords.language.es.SpanishWrittenAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpanishWrittenAmountTest {

    private AbstractWrittenAmount extensor;

    @BeforeEach
    public void setUp() {
        extensor = new SpanishWrittenAmount(new SpanishNumberDefinitions());
    }

    @ParameterizedTest
    @CsvSource({
            "-0.05, 'Menos Cinco Centavos'",
            "-1.00, 'Menos Un Peso'",
            "0.01, 'Un Centavo'",
            "0.99, 'Noventa y Nueve Centavos'",
            "0.25, 'Veinticinco Centavos'",
            "0.05, 'Cinco Centavos'",
            "1.00, 'Un Peso'",
            "10.00, 'Diez Pesos'",
            "11.01, 'Once Pesos con Un Centavo'",
            "25.50, 'Veinticinco Pesos con Cincuenta Centavos'",
            "-1000000000.00, 'Menos Mil Millones Pesos'",
            "-500000000000.05, 'Menos Quinientos Mil Millones Pesos con Cinco Centavos'",
            "999999999999.99, 'Novecientos Noventa y Nueve Mil Novecientos Noventa y Nueve Millones Novecientos Noventa y Nueve Mil Novecientos Noventa y Nueve Pesos con Noventa y Nueve Centavos'",
            "5001000103.04, 'Cinco Mil Un Millón Ciento Tres Pesos con Cuatro Centavos'",
            "5001012000.03, 'Cinco Mil Un Millón Doce Mil Pesos con Tres Centavos'",
            "5001012000.00, 'Cinco Mil Un Millón Doce Mil Pesos'",
            "5001012000.10, 'Cinco Mil Un Millón Doce Mil Pesos con Diez Centavos'",
            "5000000000.00, 'Cinco Mil Millones Pesos'",
            "1002210.00, 'Un Millón Dos Mil Doscientos Diez Pesos'",
            "1002232.00, 'Un Millón Dos Mil Doscientos Treinta y Dos Pesos'",
            "741000001002.00, 'Setecientos Cuarenta y Un Mil Millones Mil Dos Pesos'",
            "1002200.00, 'Un Millón Dos Mil Doscientos Pesos'",
            "1002200.10, 'Un Millón Dos Mil Doscientos Pesos con Diez Centavos'",
            "5000000012.08, 'Cinco Mil Millones Doce Pesos con Ocho Centavos'",
            "5000001012.15, 'Cinco Mil Millones Mil Doce Pesos con Quince Centavos'",
            "1004000000.00, 'Mil Cuatro Millones Pesos'",
            "1000003.15, 'Un Millón Tres Pesos con Quince Centavos'",
            "1500010.50, 'Un Millón Quinientos Mil Diez Pesos con Cincuenta Centavos'",
            "741001001002.00, 'Setecientos Cuarenta y Un Mil Un Millón Mil Dos Pesos'",
            "1000000000.00, 'Mil Millones Pesos'",
            "2000000001.99, 'Dos Mil Millones Un Pesos con Noventa y Nueve Centavos'",
            "1000000010.25, 'Mil Millones Diez Pesos con Veinticinco Centavos'",
            "1000000000000.00, 'Un Billón Pesos'",
            "1234567890123.45, 'Un Billón Doscientos Treinta y Cuatro Mil Quinientos Sesenta y Siete Millones Ochocientos Noventa Mil Ciento Veintitres Pesos con Cuarenta y Cinco Centavos'",
            "5000000000000.75, 'Cinco Billones Pesos con Setenta y Cinco Centavos'",
            "1500.00, 'Mil Quinientos Pesos'",
            "75000.99, 'Setenta y Cinco Mil Pesos con Noventa y Nueve Centavos'"
    })
    @DisplayName("Prueba de conversión de números a extenso")
    public void testConversiones(String input, String expected) {
        extensor.setValue(new BigDecimal(input));
        assertEquals(expected, extensor.convertToWords());
    }

}
