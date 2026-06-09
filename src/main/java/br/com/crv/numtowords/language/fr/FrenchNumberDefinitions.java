package br.com.crv.numtowords.language.fr;

import br.com.crv.numtowords.language.AbstractNumberDefinitions;

public class FrenchNumberDefinitions extends AbstractNumberDefinitions {

    public FrenchNumberDefinitions() {
        ONES = new String[]{
                "", "Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf", "Dix",
                "Onze", "Douze", "Treize", "Quatorze", "Quinze", "Seize", "Dix-Sept", "Dix-Huit", "Dix-Neuf"
        };


        TENS = new String[]{
                "", "", "Vingt", "Trente", "Quarante", "Cinquante", "Soixante", "Soixante-Dix",
                "Quatre-Vingts", "Quatre-Vingt-Dix"
        };

        HUNDREDS = new String[]{
                "", "Cent", "Deux Cent", "Trois Cent", "Quatre Cent", "Cinq Cent",
                "Six Cent", "Sept Cent", "Huit Cent", "Neuf Cent"
        };

        SCALE_GROUPS = new String[][]{
                {"Centime", "Centimes"},
                {"Euro", "Euros"},
                {"Mille", "Mille"},
                {"Million", "Millions"},
                {"Milliard", "Milliards"},
                {"Billion", "Billions"},
                {"Trillion", "Trillions"}
        };
    }

    @Override
    public String getNegativePrevix() {
        return "Moins";
    }

    @Override
    public String getCentsConnector() {
        return "et";
    }

}
