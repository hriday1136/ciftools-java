package org.rcsb.cif.model.builder;

import org.rcsb.cif.model.BlockBuilder;
import org.rcsb.cif.model.CategoryBuilder;
import org.rcsb.cif.model.CifFileBuilder;
import org.rcsb.cif.model.StrColumn;
import org.rcsb.cif.model.StrColumnBuilder;
import org.rcsb.cif.model.ValueKind;

import java.util.ArrayList;
import java.util.List;

import static org.rcsb.cif.model.CategoryBuilder.createColumnText;

public class StrColumnBuilderImpl<P extends CategoryBuilder<PP, PPP>, PP extends BlockBuilder<PPP>, PPP extends CifFileBuilder>
        extends ColumnBuilderImpl<P, PP, PPP> implements StrColumnBuilder<P, PP, PPP> {
    private final ArrayList<String> values;

    public StrColumnBuilderImpl(String categoryName, String columnName, P parent) {
        super(categoryName, columnName, parent);
        this.values = new ArrayList<>();
    }

    @Override
    public List<String> getValues() {
        return values;
    }

    @Override
    public StrColumnBuilder<P, PP, PPP> markNextNotPresent() {
        values.add(null);
        mask.add(ValueKind.NOT_PRESENT);
        return this;
    }

    @Override
    public StrColumnBuilder<P, PP, PPP> markNextUnknown() {
        values.add(null);
        mask.add(ValueKind.UNKNOWN);
        return this;
    }

    @Override
    public StrColumn build() {
        return createColumnText(getColumnName(), values, mask, StrColumn.class);
    }

    /**
     * Add one or more string values to this column.
     * <p>
     * CIF has two special tokens for missing data: {@code "."} (not present) and {@code "?"}
     * (unknown). This method treats those tokens (and {@code null}) as missingness indicators
     * rather than literal payload:
     * <ul>
     *   <li>{@code "."} -> {@link ValueKind#NOT_PRESENT}</li>
     *   <li>{@code "?"} or {@code null} -> {@link ValueKind#UNKNOWN}</li>
     *   <li>anything else -> {@link ValueKind#PRESENT} (stored verbatim)</li>
     * </ul>
     * <p>
     * Note: this means you cannot write a literal value that is exactly {@code "."} or {@code "?"}
     * via this overload. If you need explicit control over missingness vs. payload, prefer the
     * forthcoming masked overload that accepts {@link ValueKind} alongside values (e.g. {@code addMasked(...)}).
     *
     * @param values string values to append; {@code null}, {@code "."}, and {@code "?"} are treated specially
     * @return this builder instance
     */
    @Override
    public StrColumnBuilder<P, PP, PPP> add(String... values) {
        this.values.ensureCapacity(this.values.size() + values.length);
        this.mask.ensureCapacity(this.mask.size() + values.length);

        for (String s : values) {
            ValueKind kind = ValueKind.fromCifToken(s);
            switch (kind) {
                case NOT_PRESENT:
                    markNextNotPresent();
                    break;
                case UNKNOWN:
                    markNextUnknown();
                    break;
                case PRESENT:
                    this.values.add(s);
                    this.mask.add(ValueKind.PRESENT);
                    break;
                default:
                    throw new IllegalStateException("Unhandled ValueKind: " + kind);
            }
        }
        return this;
    }

    @Override
    public P leaveColumn() {
        if (parent == null) {
            throw new IllegalStateException("cannot leave column with undefined parent category");
        }
        parent.digest(this);
        return parent;
    }
}
