package br.com.crv.numtowords.tests;

import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.pt.PortugueseNumberDefinitions;
import br.com.crv.numtowords.language.pt.PortugueseWrittenAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class GeneralTest {

    private AbstractWrittenAmount extensor;

    @BeforeEach
    public void setUp() {
        extensor = new PortugueseWrittenAmount(new PortugueseNumberDefinitions());
    }

    @Test
    public void testSetValueShouldThrowExceptionForNullOrZero() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> extensor.setValue(null));
        assertEquals("setValue() cannot accept null or zero values", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> extensor.setValue(BigDecimal.ZERO));
        assertEquals("setValue() cannot accept null or zero values", exception2.getMessage());
    }

    @Test
    public void testSetValueThrowsIllegalArgumentWhenExceedsLimit() {
        BigDecimal excessiveValue = AbstractWrittenAmount.MAX_VALUE.add(BigDecimal.ONE);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> extensor.setValue(excessiveValue));
        String expected = "Value exceeds the maximum allowed (" + AbstractWrittenAmount.MAX_SCALE_NAME + ")";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void testSetValueShouldAcceptMaxValue() {
        assertDoesNotThrow(() -> extensor.setValue(AbstractWrittenAmount.MAX_VALUE));
    }

    @Test
    public void testConvertToWordThrowsIllegalStateWhenValueNotSet() {
        BigDecimal excessiveValue = AbstractWrittenAmount.MAX_VALUE.add(BigDecimal.ONE);

        Exception exception = assertThrows(IllegalStateException.class, () -> extensor.convertToWords());
        String expected = "Conversion failed: no value has been set";
        assertEquals(expected, exception.getMessage());
    }

}
