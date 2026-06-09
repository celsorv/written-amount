    package br.com.crv.numtowords.language;

    import br.com.crv.numtowords.enums.SC;
    import br.com.crv.numtowords.enums.TF;

    import java.math.BigDecimal;
    import java.math.BigInteger;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.function.Function;
    import java.util.stream.Collectors;
    import java.util.stream.IntStream;

    /**
     * Author: Celso R. Vitorino (github.com/celsorv)
     * Created: December 2025
     */
    public abstract class AbstractWrittenAmount {

        public static final BigDecimal MAX_VALUE =
                BigDecimal.TEN.pow(3 * (SC.values().length - 1)).subtract(BigDecimal.valueOf(0.01));

        public static final String MAX_SCALE_NAME = SC.values()[SC.values().length - 1].name();

        protected boolean pluralize;
        protected boolean hasNegativeSign;
        protected boolean hasNonZeroIntegerPart;
        protected boolean isMillionOrMore;
        protected List<String> wordGroups;

        private AbstractNumberDefinitions numberDefinitions;
        private List<Integer> parts;
        private BigInteger integers;
        private int decimalsValue;
        private int hundredsDigit;
        private int tensDigit;
        private int onesDigit;

        public AbstractWrittenAmount(AbstractNumberDefinitions numberDefinitions) {
            this.numberDefinitions = numberDefinitions;
        }

        public void setValue(BigDecimal value) {
            validateValue(value);
            parseValue(value);
        }

        public final String convertToWords() {
            if (integers == null) throw new IllegalStateException("Conversion failed: no value has been set");

            splitIntoGroupsOfThree();

            // adiciona a parte decimal
            parts.add(0, decimalsValue);

            formatAllGroups();
            adjustAfterFormatting();

            Collections.reverse(wordGroups);

            return wordGroups.stream()
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.joining(" "));
        }

        protected void addString(StringBuilder sb, String text, String connector) {
            if (connector != null && sb.length() > 0) sb.append(connector);
            sb.append(text);
        }

        protected void addString(StringBuilder sb, String text) {
            addString(sb, text, null);
        }

        protected int findFirstMatchingPart(int startIndex, Function<Integer, Boolean> conditions) {
            return IntStream.range(startIndex, parts.size())
                    .filter(i -> conditions.apply(parts.get(i)))
                    .findFirst()
                    .orElse(-1);
        }

        abstract protected String formatWord(int groupIndex, int previousValue);

        protected void adjustAfterFormatting() {
            fixCurrencyLabel();
            fixCentsConnector();
            fixNegativeSign();
        }

        protected void fixCurrencyLabel() {
            boolean hasThousandsOrAbove = wordGroups.size() > SC.THOUSANDS.ordinal();
            boolean shouldFixCurrencyLabel = hasCurrencyLabel() && hasNot(SC.UNITS);

            if (hasThousandsOrAbove && shouldFixCurrencyLabel) {
                String currencyLabel = getCurrencyLabel();
                wordGroups.set(1, currencyLabel);
            }
        }

        protected void fixNegativeSign() {
            if (hasNegativeSign) {
                int firstNonZeroGroup = hasNonZeroIntegerPart ? wordGroups.size() - 1 : 0;
                wordGroups.set(firstNonZeroGroup, getNegativePrefix() + " " + wordGroups.get(firstNonZeroGroup));
            }
        }

        protected void fixIntegerGroupsConnector(String initialConnector, int firstIndexGroup) {
            boolean isFirst = true;

            for (int i = firstIndexGroup; i < wordGroups.size(); i++) {
                SC scale = SC.getEnumByOrdinal(i);

                if (hasNot(scale)) continue;

                if (isFirst) {
                    wordGroups.set(i, wordGroups.get(i) + initialConnector);
                    isFirst = false;
                } else {
                    wordGroups.set(i, wordGroups.get(i) + ",");
                }
            }
        }

        protected void fixCentsConnector() {
            if (hasNonZeroIntegerPart && has(SC.CENTS)) {
                wordGroups.set(0, getCentsConnector() + " " + wordGroups.get(0));
            }
        }

        protected String getCurrencyLabel() {
            return getScaleGroupsWord(SC.UNITS);
        }

        private void validateValue(BigDecimal value) {
            if (value == null || value.compareTo(BigDecimal.ZERO) == 0)
                throw new IllegalArgumentException("setValue() cannot accept null or zero values");

            if (value.abs().compareTo(MAX_VALUE) > 0)
                throw new IllegalArgumentException("Value exceeds the maximum allowed (" + MAX_SCALE_NAME + ")");
        }

        private void parseValue(BigDecimal value) {
            hasNegativeSign = value.signum() < 0;
            value = value.abs();

            integers = value.toBigInteger();

            decimalsValue = value.remainder(BigDecimal.ONE)
                    .multiply(BigDecimal.valueOf(100))
                    .intValue();

            pluralize = integers.compareTo(BigInteger.ONE) > 0;
            hasNonZeroIntegerPart = integers.compareTo(BigInteger.ONE) >= 0;
            isMillionOrMore = integers.compareTo(BigInteger.valueOf(1_000_000)) >= 0;
        }

        private void splitIntoGroupsOfThree() {
            parts = new ArrayList<>();

            BigInteger temp = new BigInteger(integers.toString());
            BigInteger divisor = BigInteger.valueOf(1000);

            while (temp.compareTo(BigInteger.ZERO) > 0) {
                parts.add(temp.mod(divisor).intValue());
                temp = temp.divide(divisor);
            }

            // adiciona zero nos inteiros
            if (parts.isEmpty()) parts.add(0);
        }

        private void decomposeNumber(int value) {
            hundredsDigit = value / 100;
            tensDigit = value % 100;

            if (tensDigit > 19) {
                onesDigit = tensDigit % 10;
                tensDigit = tensDigit / 10;
            } else {
                onesDigit = tensDigit;
                tensDigit = 0;
            }
        }

        private void formatAllGroups() {
            wordGroups = new ArrayList<>();
            int previousValue = 0;

            for (int i = 0; i < parts.size(); i++) {
                int value = parts.get(i);

                if (value == 0) {
                    previousValue = value;
                    wordGroups.add("");
                    continue;
                }

                decomposeNumber(value);

                String str = formatWord(i, previousValue);
                wordGroups.add(str);

                previousValue = value;
            }
        }

        protected final String getScaleGroupsWord(int groupIndex) {
            SC sc = SC.getEnumByOrdinal(groupIndex);
            return getScaleGroupsWord(sc);
        }

        protected final String getScaleGroupsWord(SC sc) {
            int value = getParts(sc);
            TF tf = value > 1 || SC.UNITS.equals(sc) && pluralize ? TF.PLURAL : TF.SINGULAR;
            return numberDefinitions.getScaleGroupsWord(sc, tf);
        }

        protected final String getOnesWord(int index) { return numberDefinitions.getOnesWord(index); }
        protected final String getTensWord(int index) { return numberDefinitions.getTensWord(index); }
        protected final String getHundredsWord(int index) { return numberDefinitions.getHundredsWord(index); }
        protected final String getNegativePrefix() { return numberDefinitions.getNegativePrevix(); }
        protected final String getCentsConnector() { return numberDefinitions.getCentsConnector(); }

        protected final int getHundredsDigit() { return hundredsDigit; }
        protected final int getTensDigit() { return tensDigit; }
        protected final int getOnesDigit() { return onesDigit; }

        protected final int getParts(SC sc) { return parts.get(sc.ordinal()); }
        protected final BigInteger getIntegers() { return integers; }
        protected final int getDecimalsValue() { return decimalsValue; }

        protected final boolean hasHundredsDigit() { return hundredsDigit != 0; }
        protected final boolean hasTensDigit() { return tensDigit != 0; }
        protected final boolean hasOnesDigit() { return onesDigit != 0; }

        protected final boolean hasCurrencyLabel() {  return numberDefinitions.hasCurrencyLabel(); }

        protected final boolean hasNot(SC sc) { return !has(sc); }

        protected final boolean has(SC sc) {
            int index = sc.ordinal();
            return index < parts.size() && parts.get(index) != 0;
        }

    }
