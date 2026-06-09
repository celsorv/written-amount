package br.com.crv.numtowords.language.en;

import br.com.crv.numtowords.enums.SC;
import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.AbstractNumberDefinitions;

/**
 * Author: Celso R. Vitorino (github.com/celsorv)
 * Created: December 2025
 */
public class EnglishWrittenAmount extends AbstractWrittenAmount {

    public EnglishWrittenAmount(AbstractNumberDefinitions numberDefinitions) {
        super(numberDefinitions);
    }

    @Override
    protected String formatWord(int groupIndex, int previousValue) {
        StringBuilder sb = new StringBuilder();

        if (hasHundredsDigit()) {
            addString(sb, getHundredsWord(getHundredsDigit()));
        }

        if (hasTensDigit()) {
            addString(sb, getTensWord(getTensDigit()), " ");
        }

        if (hasOnesDigit()) {
            if (hasTensDigit()) {
                addString(sb, "-" + getOnesWord(getOnesDigit()));
            } else {
                addString(sb, getOnesWord(getOnesDigit()), " ");
            }
        }

        String scaleGroup = getScaleGroupsWord(groupIndex);
        if (scaleGroup.length() > 0) {
            addString(sb, scaleGroup, " ");
        }

        return sb.toString();
    }

    @Override
    protected void adjustAfterFormatting() {
        fixIntegerGroupsConnector(
                getInitialConnector(),
                has(SC.UNITS) ? SC.THOUSANDS.ordinal() : SC.MILLIONS.ordinal()
        );
        super.adjustAfterFormatting();
    }

    private String getInitialConnector() {
        if ((has(SC.UNITS) || has(SC.THOUSANDS))) {
            int currencyValue = getParts(SC.UNITS);

            if (currencyValue == 0 || (currencyValue >= 100 && currencyValue % 100 != 0)) {
                return ",";
            }
        }

        return "";
    }

}
