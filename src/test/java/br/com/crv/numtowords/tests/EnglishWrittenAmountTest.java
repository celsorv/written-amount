package br.com.crv.numtowords.tests;

import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.en.EnglishNumberDefinitions;
import br.com.crv.numtowords.language.en.EnglishWrittenAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnglishWrittenAmountTest {

    private AbstractWrittenAmount extensor;

    @BeforeEach
    public void setUp() {
        extensor = new EnglishWrittenAmount(new EnglishNumberDefinitions());
    }

    @ParameterizedTest
    @CsvSource({
            "-0.05, 'Minus Five Cents'",
            "-1.00, 'Minus One Dollar'",
            "0.05, 'Five Cents'",
            "1.00, 'One Dollar'",
            "1000.00, 'One Thousand Dollars'",
            "1000000.00, 'One Million Dollars'",
            "1000000000.00, 'One Billion Dollars'",
            "1000000000000.00, 'One Trillion Dollars'",
            "5001000103.04, 'Five Billion, One Million, One Hundred Three Dollars and Four Cents'",
            "5001012000.03, 'Five Billion, One Million, Twelve Thousand Dollars and Three Cents'",
            "5001012000.00, 'Five Billion, One Million, Twelve Thousand Dollars'",
            "5001012000.10, 'Five Billion, One Million, Twelve Thousand Dollars and Ten Cents'",
            "1002210.00, 'One Million, Two Thousand, Two Hundred Ten Dollars'",
            "1002232.00, 'One Million, Two Thousand, Two Hundred Thirty-Two Dollars'",
            "741000001002.00, 'Seven Hundred Forty-One Billion, One Thousand Two Dollars'",
            "1002200.00, 'One Million, Two Thousand Two Hundred Dollars'",
            "1002200.10, 'One Million, Two Thousand Two Hundred Dollars and Ten Cents'",
            "5000000012.08, 'Five Billion Twelve Dollars and Eight Cents'",
            "5000001012.15, 'Five Billion, One Thousand Twelve Dollars and Fifteen Cents'",
            "1004000000.00, 'One Billion, Four Million Dollars'",
            "741000001102.00, 'Seven Hundred Forty-One Billion, One Thousand, One Hundred Two Dollars'",
            "1001005.00, 'One Million, One Thousand Five Dollars'",
            "1234567890123.45, 'One Trillion, Two Hundred Thirty-Four Billion, Five Hundred Sixty-Seven Million, Eight Hundred Ninety Thousand, One Hundred Twenty-Three Dollars and Forty-Five Cents'",
            "5000001000000.00, 'Five Trillion, One Million Dollars'",
            "5000001000001.00, 'Five Trillion, One Million One Dollars'",
            "5000001000100.00, 'Five Trillion, One Million One Hundred Dollars'",
            "5000001001000.00, 'Five Trillion, One Million, One Thousand Dollars'",
            "5000001001001.00, 'Five Trillion, One Million, One Thousand One Dollars'",
            "5000001000000.10, 'Five Trillion, One Million Dollars and Ten Cents'",
            "5000001001001.10, 'Five Trillion, One Million, One Thousand One Dollars and Ten Cents'"
    })
    @DisplayName("Test conversion of numbers to words in English with varied connectors")
    public void testConversions(String input, String expected) {
        extensor.setValue(new BigDecimal(input));
        assertEquals(expected, extensor.convertToWords());
    }

}
