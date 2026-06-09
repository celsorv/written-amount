package br.com.crv.numtowords.language.pt;

import br.com.crv.numtowords.enums.SC;
import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.AbstractNumberDefinitions;

import java.math.BigInteger;

public class PortugueseWrittenAmount extends AbstractWrittenAmount {

    public PortugueseWrittenAmount(AbstractNumberDefinitions numberDefinitions) {
        super(numberDefinitions);
    }

    @Override
    public String formatWord(int groupIndex, int previousValue) {
        StringBuilder sb = new StringBuilder();

        if (getHundredsDigit() == 1 && !hasTensDigit() && !hasOnesDigit()) {
            addString(sb, "Cem");
        } else {
            if (hasHundredsDigit()) {
                addString(sb, getHundredsWord(getHundredsDigit()));
            }

            if (hasTensDigit()) {
                addString(sb, getTensWord(getTensDigit()), " e ");
            }

            if (hasOnesDigit()) {
                boolean omitOneInThousand = (groupIndex == SC.THOUSANDS.ordinal() && getParts(SC.THOUSANDS) == 1);
                if (!omitOneInThousand) addString(sb, getOnesWord(getOnesDigit()), " e ");
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
                getFirstIndexGroup()
        );
        super.adjustAfterFormatting();
    }

    @Override
    protected String getCurrencyLabel() {
        return (needExpressionDeReais() ? "de " : "") + getScaleGroupsWord(SC.UNITS);
    }

    @Override
    protected void fixCentsConnector() {
        if (!has(SC.CENTS)) return;

        String cents = wordGroups.get(0);
        if (hasNonZeroIntegerPart) {
            wordGroups.set(0, "e " + cents);
        } else {
            wordGroups.set(0, cents + " de " + getScaleGroupsWord(SC.UNITS));
        }
    }

    private String getInitialConnector() {
        int currencyValue = getParts(SC.UNITS);

        if (has(SC.UNITS) && has(SC.THOUSANDS)) {
            return (currencyValue < 100 || currencyValue % 100 == 0) ? " e" : "";
        }

        if (isMillionOrMore && currencyValue == 1) {
            return " e";
        }

        return hasNot(SC.CENTS) ? " e" : ",";
    }

    private int getFirstIndexGroup() {
        if (needExpressionDeReais())
            return 1 + findFirstMatchingPart(SC.MILLIONS.ordinal(), n -> n != 0);

        return has(SC.UNITS) ? SC.THOUSANDS.ordinal() : SC.MILLIONS.ordinal();
    }

    /**
     * Determina se a expressão "de Reais" deve ser usada.
     * A condição ocorre quando o valor é um múltiplo exato de um milhão.
     */
    private boolean needExpressionDeReais() {
        BigInteger base = BigInteger.valueOf(1_000_000);
        return hasNonZeroIntegerPart && getIntegers().subtract(base).mod(base).equals(BigInteger.ZERO);
    }

}
