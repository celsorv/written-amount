package br.com.crv.numtowords.tests;

import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.pt.PortugueseNumberDefinitions;
import br.com.crv.numtowords.language.pt.PortugueseWrittenAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortugueseWrittenAmountTest {

    private AbstractWrittenAmount extensor;

    @BeforeEach
    public void setUp() {
        extensor = new PortugueseWrittenAmount(new PortugueseNumberDefinitions());
    }

    @ParameterizedTest
    @CsvSource({
            "5001000000.00, 'Cinco Bilhões e Um Milhão de Reais'",
            "5001000103.04, 'Cinco Bilhões, Um Milhão, Cento e Três Reais e Quatro Centavos'",
            "5001012000.03, 'Cinco Bilhões, Um Milhão, Doze Mil Reais e Três Centavos'",
            "5001012000.00, 'Cinco Bilhões, Um Milhão e Doze Mil Reais'",
            "5000000000.00, 'Cinco Bilhões de Reais'",
            "1002210.00, 'Um Milhão, Dois Mil Duzentos e Dez Reais'",
            "1002232.00, 'Um Milhão, Dois Mil Duzentos e Trinta e Dois Reais'",
            "741000001002.00, 'Setecentos e Quarenta e Um Bilhões, Mil e Dois Reais'",
            "1002200.00, 'Um Milhão, Dois Mil e Duzentos Reais'",
            "1002200.10, 'Um Milhão, Dois Mil e Duzentos Reais e Dez Centavos'",
            "5000000012.08, 'Cinco Bilhões, Doze Reais e Oito Centavos'",
            "5000001012.15, 'Cinco Bilhões, Mil e Doze Reais e Quinze Centavos'",
            "1004000000.00, 'Um Bilhão e Quatro Milhões de Reais'",
            "100118100105.01, 'Cem Bilhões, Cento e Dezoito Milhões, Cem Mil Cento e Cinco Reais e Um Centavo'",
            "100118100105.00, 'Cem Bilhões, Cento e Dezoito Milhões, Cem Mil Cento e Cinco Reais'",
            "100118100000.00, 'Cem Bilhões, Cento e Dezoito Milhões e Cem Mil Reais'",
            "1000000001001.00, 'Um Trilhão, Mil e Um Reais'",
            "1000001000001.00, 'Um Trilhão, Um Milhão e Um Reais'",
            "1000001000001.10, 'Um Trilhão, Um Milhão e Um Reais e Dez Centavos'",
            "1000000000000000.00, 'Um Quatrilhão de Reais'",
            "50000010001.00, 'Cinquenta Bilhões, Dez Mil e Um Reais'",
            "50000000001.00, 'Cinquenta Bilhões e Um Reais'",
            "50000000001.06, 'Cinquenta Bilhões e Um Reais e Seis Centavos'",
            "50000000002.06, 'Cinquenta Bilhões, Dois Reais e Seis Centavos'",
            "5000000000001001001.00, 'Cinco Quintilhões, Um Milhão, Mil e Um Reais'",
            "5000000000001000000.01, 'Cinco Quintilhões, Um Milhão de Reais e Um Centavo'",
            "0.05, 'Cinco Centavos de Real'",
            "1.00, 'Um Real'",
            "-0.05, 'Menos Cinco Centavos de Real'",
            "-1.00, 'Menos Um Real'"
    })
    @DisplayName("Teste de conversão de números para extenso")
    public void testConversoes(String input, String expected) {
        extensor.setValue(new BigDecimal(input));
        assertEquals(expected, extensor.convertToWords());
    }
}
