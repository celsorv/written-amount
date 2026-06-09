package br.com.crv.numtowords.language.es;

import br.com.crv.numtowords.language.AbstractNumberDefinitions;

public class SpanishNumberDefinitions extends AbstractNumberDefinitions {

    public SpanishNumberDefinitions() {
        ONES = new String[]{
                "", "Un", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez",
                "Once", "Doce", "Trece", "Catorce", "Quince", "Dieciséis", "Diecisiete", "Dieciocho", "Diecinueve"
        };

        TENS = new String[]{
                "", "", "Veinte", "Treinta", "Cuarenta", "Cincuenta", "Sesenta", "Setenta", "Ochenta", "Noventa"
        };

        HUNDREDS = new String[]{
                "", "Ciento", "Doscientos", "Trescientos", "Cuatrocientos", "Quinientos",
                "Seiscientos", "Setecientos", "Ochocientos", "Novecientos"
        };

        SCALE_GROUPS = new String[][]{
                {"Centavo", "Centavos"},
                {"Peso", "Pesos"},
                {"Mil", "Mil"},
                {"Millón", "Millones"},
                {"Mil Millones", "Mil Millones"},
                {"Billón", "Billones"},
                {"Mil Billones", "Mil Billones"},
                {"Trillón", "Trillones"},
                {"Cuatrillón", "Cuatrillones"},
                {"Quintillón", "Quintillones"}
        };
    }

    @Override
    public String getNegativePrevix() { return "Menos"; }

    @Override
    public String getCentsConnector() { return "con"; }

}
