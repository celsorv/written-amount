package br.com.crv.numtowords.tests;

import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.fr.FrenchNumberDefinitions;
import br.com.crv.numtowords.language.fr.FrenchWrittenAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrenchWrittenAmountTest {

    private AbstractWrittenAmount extensor;

    @BeforeEach
    public void setUp() {
        extensor = new FrenchWrittenAmount(new FrenchNumberDefinitions());
    }

    @ParameterizedTest
    @CsvSource({
            "-0.05, 'Moins Cinq Centimes'",
            "-1.00, 'Moins Un Euro'",
            "0.05, 'Cinq Centimes'",
            "1.00, 'Un Euro'",
            "1000.00, 'Mille Euros'",
            "1000000.00, 'Un Million Euros'",
            "1000000000.00, 'Un Milliard Euros'",
            "1000000000000.00, 'Un Billion Euros'",
            "5001000103.04, 'Cinq Milliards Un Million Cent Trois Euros et Quatre Centimes'",
            "5001012000.03, 'Cinq Milliards Un Million Douze Mille Euros et Trois Centimes'",
            "5001012000.00, 'Cinq Milliards Un Million Douze Mille Euros'",
            "5001012000.10, 'Cinq Milliards Un Million Douze Mille Euros et Dix Centimes'",
            "1002210.00, 'Un Million Deux Mille Deux Cent Dix Euros'",
            "1002232.00, 'Un Million Deux Mille Deux Cent Trente-Deux Euros'",
            "741000001002.00, 'Sept Cent Quarante et Un Milliards Mille Deux Euros'",
            "1002200.00, 'Un Million Deux Mille Deux Cents Euros'",
            "1002200.10, 'Un Million Deux Mille Deux Cents Euros et Dix Centimes'",
            "5000000012.08, 'Cinq Milliards Douze Euros et Huit Centimes'",
            "5000001012.15, 'Cinq Milliards Mille Douze Euros et Quinze Centimes'",
            "1004000000.00, 'Un Milliard Quatre Millions Euros'",
            "741000001102.00, 'Sept Cent Quarante et Un Milliards Mille Cent Deux Euros'",
            "1001005.00, 'Un Million Mille Cinq Euros'",
            "1234567890123.45, 'Un Billion Deux Cent Trente-Quatre Milliards Cinq Cent Soixante-Sept " +
                    "Millions Huit Cent Quatre-Vingt-Dix Mille Cent Vingt-Trois Euros et Quarante-Cinq Centimes'",
            "5000001000000.00, 'Cinq Billions Un Million Euros'",
            "5000001000001.00, 'Cinq Billions Un Million Un Euros'",
            "5000001000100.00, 'Cinq Billions Un Million Cent Euros'",
            "5000001001000.00, 'Cinq Billions Un Million Mille Euros'",
            "5000001001001.00, 'Cinq Billions Un Million Mille Un Euros'",
            "5000001000000.10, 'Cinq Billions Un Million Euros et Dix Centimes'",
            "5000001001001.10, 'Cinq Billions Un Million Mille Un Euros et Dix Centimes'"
    })
    @DisplayName("Test conversion of numbers to words in French with varied connectors")
    public void testConversions(String input, String expected) {
        extensor.setValue(new BigDecimal(input));
        assertEquals(expected, extensor.convertToWords());
    }

}
