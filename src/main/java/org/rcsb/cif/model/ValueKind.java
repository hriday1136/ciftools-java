package org.rcsb.cif.model;

/**
 * Reports whether a certain row in a certain {@link Column} is present.
 */
public enum ValueKind {
    /**
     * The value is present.
     */
    PRESENT,
    /**
     * The value is explicitly not present - <code>.</code> in CIF. String values will be empty, number values will be
     * 0.
     */
    NOT_PRESENT,
    /**
     * The value is unknown - <code>?</code> in CIF. String values will be empty, number values will be 0.
     */
    UNKNOWN;

    public static final String CIF_NOT_PRESENT = ".";
    public static final String CIF_UNKNOWN = "?";

    /**
     * Checks whether a String matches "?" or ".", sequences with special meaning in CIF.
     * @param s payload to evaluate
     * @return true if this String indicates missing or undefined values
     */
    public static boolean isValueKindToken(String s) {
        return CIF_NOT_PRESENT.equals(s) || CIF_UNKNOWN.equals(s);
    }

    /**
     * Transforms a String into a ValueKind.
     * @param s payload to evaluate
     * @return appropriate ValueKind for "?" and ".", otherwise marked as PRESENT
     */
    public static ValueKind fromCifToken(String s) {
        if (s == null || CIF_NOT_PRESENT.equals(s)) return NOT_PRESENT;
        if (CIF_UNKNOWN.equals(s)) return UNKNOWN;
        return PRESENT;
    }
}
