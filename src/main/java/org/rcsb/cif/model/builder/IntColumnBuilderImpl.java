package org.rcsb.cif.model.builder;

import org.rcsb.cif.model.BlockBuilder;
import org.rcsb.cif.model.CategoryBuilder;
import org.rcsb.cif.model.CifFileBuilder;
import org.rcsb.cif.model.IntColumn;
import org.rcsb.cif.model.IntColumnBuilder;
import org.rcsb.cif.model.ValueKind;

import java.util.ArrayList;
import java.util.List;

import static org.rcsb.cif.model.CategoryBuilder.createColumnText;

public class IntColumnBuilderImpl<P extends CategoryBuilder<PP, PPP>, PP extends BlockBuilder<PPP>, PPP extends CifFileBuilder>
        extends ColumnBuilderImpl<P, PP, PPP> implements IntColumnBuilder<P, PP, PPP> {
    private final ArrayList<Integer> values;

    public IntColumnBuilderImpl(String categoryName, String columnName, P parent) {
        super(categoryName, columnName, parent);
        this.values = new ArrayList<>();
    }

    @Override
    public List<Integer> getValues() {
        return values;
    }

    @Override
    public IntColumnBuilder<P, PP, PPP> markNextNotPresent() {
        values.add(null);
        mask.add(ValueKind.NOT_PRESENT);
        return this;
    }

    @Override
    public IntColumnBuilder<P, PP, PPP> markNextUnknown() {
        values.add(null);
        mask.add(ValueKind.UNKNOWN);
        return this;
    }

    @Override
    public IntColumn build() {
        return createColumnText(getColumnName(), values, mask, IntColumn.class);
    }

    @Override
    public IntColumnBuilder<P, PP, PPP> add(int... values) {
        this.values.ensureCapacity(this.values.size() + values.length);
        this.mask.ensureCapacity(this.mask.size() + values.length);

        for (int v : values) {
            this.values.add(v);
            this.mask.add(ValueKind.PRESENT);
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
