package org.rcsb.cif.model;

import java.util.List;
import java.util.Objects;

/**
 * A builder instance for {@link StrColumn} instances.
 * @param <P> parent builder type (CategoryBuilder)
 * @param <PP> parent's parent builder type (BlockBuilder)
 * @param <PPP> parent's parent's parent builder type (CifFileBuilder)
 */
public interface StrColumnBuilder<P extends CategoryBuilder<PP, PPP>, PP extends BlockBuilder<PPP>, PPP extends CifFileBuilder> extends ColumnBuilder<P, PP, PPP> {
    /**
     * Access to all stored values.
     * @return String values
     */
    List<String> getValues();

    StrColumn build();

    StrColumnBuilder<P, PP, PPP> markNextNotPresent();

    StrColumnBuilder<P, PP, PPP> markNextUnknown();

    /**
     * Add new values to this column.
     * @param values String values
     * @return this builder instance
     */
    StrColumnBuilder<P, PP, PPP> add(String... values);

    /**
     * Add new values with fine-grained control.
     * <p>
     * For {@link ValueKind#PRESENT}, the corresponding entry from {@code values} is appended.
     * For {@link ValueKind#NOT_PRESENT} and {@link ValueKind#UNKNOWN}, this method delegates to
     * {@link #markNextNotPresent()} and {@link #markNextUnknown()} respectively.
     * </p>
     * @param values array of String values
     * @param mask array of {@link ValueKind}, must be the same length as {@code values}
     * @return this builder instance
     * @throws IllegalArgumentException if arrays differ in size
     * @throws NullPointerException if {@code values}, {@code mask}, or any {@code mask[i]} is null
     */
    default StrColumnBuilder<P,PP,PPP> addMasked(String[] values, ValueKind[] mask) {
        Objects.requireNonNull(values, "values");
        Objects.requireNonNull(mask, "mask");
        if (values.length != mask.length) {
            throw new IllegalArgumentException("values.length (" + values.length + ") must equal mask.length (" + mask.length + ")");
        }

        for (int i = 0; i < values.length; i++) {
            ValueKind k = Objects.requireNonNull(mask[i], "mask[" + i + "]");
            if (k == ValueKind.PRESENT && (values[i] == null || ValueKind.isValueKindToken(values[i]))) {
                throw new IllegalArgumentException("PRESENT value must not be null, '.' or '?': values[" + i + "]");
            }
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
     * @param values String values, null is mapped to NOT_PRESENT ("."); "." and "?" are interpreted as CIF tokens.
     * @return this builder instance
     */
    default StrColumnBuilder<P,PP,PPP> addNullable(Iterable<String> values) {
        Objects.requireNonNull(values, "values");
        for (String v : values) {
            ValueKind valueKind = ValueKind.fromCifToken(v);
            switch (valueKind) {
                case PRESENT:
                    add(v);
                    break;
                case NOT_PRESENT:
                    markNextNotPresent();
                    break;
                case UNKNOWN:
                    markNextUnknown();
                    break;
                default:
                    throw new IllegalStateException("Unhandled ValueKind: " + valueKind);
            }
        }
        return this;
    }
}
