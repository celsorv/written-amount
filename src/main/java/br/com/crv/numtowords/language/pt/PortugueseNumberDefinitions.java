package br.com.crv.numtowords.language.pt;

import br.com.crv.numtowords.language.AbstractNumberDefinitions;

public class PortugueseNumberDefinitions extends AbstractNumberDefinitions {

    public PortugueseNumberDefinitions() {
        ONES = new String[]{
                "", "Um", "Dois", "Três", "Quatro", "Cinco", "Seis", "Sete", "Oito", "Nove", "Dez",
                "Onze", "Doze", "Treze", "Quatorze", "Quinze", "Dezesseis", "Dezessete", "Dezoito", "Dezenove"
        };

        TENS = new String[]{
                "", "", "Vinte", "Trinta", "Quarenta", "Cinquenta", "Sessenta", "Setenta", "Oitenta", "Noventa"
        };

        HUNDREDS = new String[]{
                "", "Cento", "Duzentos", "Trezentos", "Quatrocentos", "Quinhentos",
                "Seiscentos", "Setecentos", "Oitocentos", "Novecentos"
        };

        SCALE_GROUPS = new String[][]{
                {"Centavo", "Centavos"},
                {"Real", "Reais"},
                {"Mil", "Mil"},
                {"Milhão", "Milhões"},
                {"Bilhão", "Bilhões"},
                {"Trilhão", "Trilhões"},
                {"Quatrilhão", "Quatrilhões"},
                {"Quintilhão", "Quintilhões"}
        };
    }

    @Override
    public String getNegativePrevix() { return "Menos"; }

    @Override
    public String getCentsConnector() { return "e"; }

}
