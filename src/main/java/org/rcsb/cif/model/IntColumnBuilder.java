package org.rcsb.cif.model;

import java.util.List;
import java.util.Objects;

/**
 * A builder instance for {@link IntColumn} instances.
 * @param <P> parent builder type (CategoryBuilder)
 * @param <PP> parent's parent builder type (BlockBuilder)
 * @param <PPP> parent's parent's parent builder type (CifFileBuilder)
 */
public interface IntColumnBuilder<P extends CategoryBuilder<PP, PPP>, PP extends BlockBuilder<PPP>, PPP extends CifFileBuilder> extends ColumnBuilder<P, PP, PPP> {
    /**
     * Access to all stored values.
     * @return int values
     */
    List<Integer> getValues();

    IntColumn build();

    IntColumnBuilder<P, PP, PPP> markNextNotPresent();

    IntColumnBuilder<P, PP, PPP> markNextUnknown();

    /**
     * Add new values to this column.
     * @param values int values
     * @return this builder instance
     */
    IntColumnBuilder<P, PP, PPP> add(int... values);

    /**
     * Add new values with fine-grained control.
     * <p>
     * For {@link ValueKind#PRESENT}, the corresponding entry from {@code values} is appended.
     * For {@link ValueKind#NOT_PRESENT} and {@link ValueKind#UNKNOWN}, this method delegates to
     * {@link #markNextNotPresent()} and {@link #markNextUnknown()} respectively.
     * </p>
     * @param values array of int values
     * @param mask array of {@link ValueKind}, must be the same length as {@code values}
     * @return this builder instance
     * @throws IllegalArgumentException if arrays differ in size
     * @throws NullPointerException if {@code values}, {@code mask}, or any {@code mask[i]} is null
     */
    default IntColumnBuilder<P,PP,PPP> addMasked(int[] values, ValueKind[] mask) {
        Objects.requireNonNull(values, "values");
        Objects.requireNonNull(mask, "mask");
        if (values.length != mask.length) {
            throw new IllegalArgumentException("values.length (" + values.length + ") must equal mask.length (" + mask.length + ")");
        }

        for (int i = 0; i < values.length; i++) {
            ValueKind k = Objects.requireNonNull(mask[i], "mask[" + i + "]");
            switch (k) {
                case PRESENT:
                    add(values[i]);
                    break;
                case NOT_PRESENT:
                    markNextNotPresent();
                    break;
                case UNKNOWN:
                    markNextUnknown();
                    break;
                default:
                    throw new IllegalStateException("Unhandled ValueKind: " + k);
            }
        }
        return this;
    }

    /**
     * Add values from an Iterable.
     * @param values Integer values, null is mapped to ValueKind.NOT_PRESENT (".")
     * @return this builder instance
     */
    default IntColumnBuilder<P,PP,PPP> addNullable(Iterable<Integer> values) {
        Objects.requireNonNull(values, "values");
        for (Integer v : values) {
            if (v == null) {
                markNextNotPresent();
            } else {
                add(v);
            }
        }
        return this;
    }
}
