package br.com.crv.numtowords.language.fr;

import br.com.crv.numtowords.enums.SC;
import br.com.crv.numtowords.language.AbstractNumberDefinitions;
import br.com.crv.numtowords.language.AbstractWrittenAmount;

/**
 * Author: Celso R. Vitorino (github.com/celsorv)
 * Created: December 2025
 */
public class FrenchWrittenAmount extends AbstractWrittenAmount {

    public FrenchWrittenAmount(AbstractNumberDefinitions numberDefinitions) {
        super(numberDefinitions);
    }

    @Override
    public String formatWord(int groupIndex, int previousValue) {
        StringBuilder sb = new StringBuilder();

        if (hasHundredsDigit()) {
            boolean needPlural = shouldPluralizeHundreds(groupIndex);
            addString(sb, getHundredsWord(getHundredsDigit()) + (needPlural ? "s" : ""));
        }

        if (getTensDigit() == 7 || getTensDigit() == 9) {
            handleSpecialTens(sb, getTensDigit());

        } else if (getTensDigit() == 8 && hasOnesDigit()) {
            handleEighty(sb);

        } else {
            if (hasTensDigit()) {
                addString(sb, getTensWord(getTensDigit()), " ");
            }

            if (hasOnesDigit()) {
                boolean omitOneInThousand = (groupIndex == SC.THOUSANDS.ordinal() && getParts(SC.THOUSANDS) == 1);
                if (!omitOneInThousand) {
                    String connector = getConnectorForOnesDigit();
                    addString(sb, getOnesWord(getOnesDigit()), connector);
                }
            }
        }

        String scaleGroup = getScaleGroupsWord(groupIndex);
        if (!scaleGroup.isEmpty()) {
            addString(sb, scaleGroup, " ");
        }

        return sb.toString();
    }

    private boolean shouldPluralizeHundreds(int groupIndex) {
        return groupIndex == SC.UNITS.ordinal() &&
                getHundredsDigit() != 1 &&
                getParts(SC.getEnumByOrdinal(groupIndex)) % 100 == 0;
    }

    private void handleSpecialTens(StringBuilder sb, int tens) {
        String tensWord = getTensWord(tens - 1);
        if (tens == 9) {
            tensWord = removeLastChar(tensWord);
        }
        String connector = (tens == 7 && getOnesDigit() == 1) ? " et " : "-";
        addString(sb, tensWord, " ");
        addString(sb, getOnesWord(getOnesDigit() + 10), connector);
    }

    private void handleEighty(StringBuilder sb) {
        String tensWord = removeLastChar(getTensWord(8));
        addString(sb, tensWord, " ");
        addString(sb, getOnesWord(getOnesDigit()), "-");
    }

    private String getConnectorForOnesDigit() {
        if (hasTensDigit()) {
            return (getTensDigit() == 1 || (getTensDigit() > 1 && getOnesDigit() == 1)) ? " et " : "-";
        }
        return " ";
    }

    private static String removeLastChar(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }
}
