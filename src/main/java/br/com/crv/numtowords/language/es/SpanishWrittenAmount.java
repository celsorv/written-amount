package br.com.crv.numtowords.language.es;

import br.com.crv.numtowords.enums.SC;
import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.AbstractNumberDefinitions;

import java.util.EnumSet;

/**
 * Author: Celso R. Vitorino (github.com/celsorv)
 * Created: December 2025
 */
public class SpanishWrittenAmount extends AbstractWrittenAmount {

    public SpanishWrittenAmount(AbstractNumberDefinitions numberDefinitions) {
        super(numberDefinitions);
    }

    @Override
    public String formatWord(int groupIndex, int previousValue) {
        StringBuilder sb = new StringBuilder();

        if (hasHundredsDigit()) {
            addString(sb, getHundredsWord(getHundredsDigit()));
        }

        if (getTensDigit() == 2 && hasOnesDigit()) {
            // Trata o caso especial do "Veintiuno", "Veintidós", etc.
            addString(sb, "Veinti" + getOnesWord(getOnesDigit()).toLowerCase(), " ");
        } else {
            if (hasTensDigit()) {
                addString(sb, getTensWord(getTensDigit()), " ");
            }

            if (hasOnesDigit()) {
                boolean omitOneInThousand = shouldOmitOneInThousand(groupIndex);
                if (!omitOneInThousand) {
                    String connector = (hasTensDigit() && getTensDigit() != 0) ? " y " : " ";
                    addString(sb, getOnesWord(getOnesDigit()), connector);
                }
            }
        }

        // Corrige duplicação de "Millones"
        if (previousValue != 0 && groupIndex == SC.BILLIONS.ordinal() || groupIndex == SC.QUADRILLIONS.ordinal()) {
            addString(sb, "Mil", " ");
        } else {
            String scaleGroup = getScaleGroupsWord(groupIndex);
            if (scaleGroup.length() > 0) {
                addString(sb, scaleGroup, " ");
            }
        }

        return sb.toString();
    }

    private boolean shouldOmitOneInThousand(int groupIndex) {
        SC scaleGroup = SC.getEnumByOrdinal(groupIndex);
        int groupValue = getParts(scaleGroup);

        if (groupValue == 1) {
            EnumSet<SC> omitUnScaleGroups = EnumSet.of(SC.THOUSANDS, SC.BILLIONS, SC.QUADRILLIONS);
            return omitUnScaleGroups.contains(scaleGroup);
        }

        return false;
    }

}
