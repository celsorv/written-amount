package br.com.crv.numtowords.enums;

public enum SC {
    CENTS,
    UNITS,
    THOUSANDS,
    MILLIONS,
    BILLIONS,
    TRILLIONS,
    QUADRILLIONS,
    QUINTILLIONS;

    private static final SC[] VALUES = SC.values();

    public static SC getEnumByOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= VALUES.length)
            throw new IllegalArgumentException("SC: invalid ordinal value: " + ordinal);

        return VALUES[ordinal];
    }
}
