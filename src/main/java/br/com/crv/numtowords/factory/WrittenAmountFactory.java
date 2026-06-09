package br.com.crv.numtowords.factory;

import br.com.crv.numtowords.language.AbstractNumberDefinitions;
import br.com.crv.numtowords.language.AbstractWrittenAmount;
import br.com.crv.numtowords.language.en.EnglishNumberDefinitions;
import br.com.crv.numtowords.language.en.EnglishWrittenAmount;
import br.com.crv.numtowords.language.es.SpanishNumberDefinitions;
import br.com.crv.numtowords.language.es.SpanishWrittenAmount;
import br.com.crv.numtowords.language.fr.FrenchNumberDefinitions;
import br.com.crv.numtowords.language.fr.FrenchWrittenAmount;
import br.com.crv.numtowords.language.pt.PortugueseNumberDefinitions;
import br.com.crv.numtowords.language.pt.PortugueseWrittenAmount;

import java.util.Locale;

/**
 * Author: Celso R. Vitorino (github.com/celsorv)
 * Created: December 2025
 */
public class WrittenAmountFactory {

    public AbstractWrittenAmount createWrittenAmount() {
        return createWrittenAmount(null);
    }

    public AbstractWrittenAmount createWrittenAmount(Locale locale) {

        if (locale == null) {
            locale = Locale.getDefault();
        }

        if (locale.getLanguage().equals("pt")) {
            return new PortugueseWrittenAmount(new PortugueseNumberDefinitions());

        } else if (locale.getLanguage().equals("en")) {
            AbstractNumberDefinitions numberDefinitions = getNumberDefinitions(locale);
            return new EnglishWrittenAmount(numberDefinitions);

        } else if (locale.getLanguage().equals("es")) {
            return new SpanishWrittenAmount(new SpanishNumberDefinitions());

        } else if (locale.getLanguage().equals("fr")) {
            return new FrenchWrittenAmount(new FrenchNumberDefinitions());

        }

        throw new IllegalArgumentException("Implementation not available for locale: " + locale);
    }

    private static AbstractNumberDefinitions getNumberDefinitions(Locale locale) {
        AbstractNumberDefinitions numberDefinitions = new EnglishNumberDefinitions();

        /* Default: inglês EUA */

        if (locale.getCountry().equals("GB")) {
            numberDefinitions.setCurrencyLabels("Pound", "Pounds");
            numberDefinitions.setCentLabels("Penny", "Pence");
        }

        return numberDefinitions;
    }
}
