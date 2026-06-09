package br.com.crv.numtowords.language;

import br.com.crv.numtowords.enums.SC;
import br.com.crv.numtowords.enums.TF;

/**
 * Author: Celso R. Vitorino (github.com/celsorv)
 * Created: December 2025
 */
public abstract class AbstractNumberDefinitions {

    protected String[] ONES = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    protected String[] TENS = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    protected String[] HUNDREDS = {
            "", "One Hundred", "Two Hundred", "Three Hundred", "Four Hundred", "Five Hundred",
            "Six Hundred", "Seven Hundred", "Eight Hundred", "Nine Hundred"
    };

    protected String[][] SCALE_GROUPS = {
            {"Cent", "Cents"},
            {"Dollar", "Dollars"},
            {"Thousand", "Thousand"},
            {"Million", "Million"},
            {"Billion", "Billion"},
            {"Trillion", "Trillion"},
            {"Quadrillion", "Quadrillion"},
            {"Quintillion", "Quintillion"}
    };

    public String getOnesWord(int index) {
        checkIndex(index, ONES);
        return ONES[index];
    }

    public String getTensWord(int index) {
        checkIndex(index, TENS);
        return TENS[index];
    }

    public String getHundredsWord(int index) {
        checkIndex(index, HUNDREDS);
        return HUNDREDS[index];
    }

    public String getScaleGroupsWord(SC sc, TF tf) {
        int index = sc.ordinal();
        checkIndex(index, SCALE_GROUPS);
        checkIndex(tf.ordinal(), SCALE_GROUPS[index]);
        return SCALE_GROUPS[index][tf.ordinal()];
    }

    public String getNegativePrevix() { return "Minus"; }

    public String getCentsConnector() { return "and"; }

    public boolean hasCurrencyLabel() { return getScaleGroupsWord(SC.UNITS, TF.PLURAL).length() > 0; }

    public void setCurrencyLabels(String singular, String plural) {
        SCALE_GROUPS[SC.UNITS.ordinal()] = new String[]{singular, plural};
    }

    public void setCentLabels(String singular, String plural) {
        SCALE_GROUPS[SC.CENTS.ordinal()] = new String[]{singular, plural};
    }

    private void checkIndex(int index, Object[] array) {
        if (index < 0 || index >= array.length)
            throw new IllegalArgumentException("Invalid index: " + index +
                    ". The index must be between 0 and " + (array.length - 1));
    }

}
