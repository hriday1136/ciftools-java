package org.rcsb.cif.schema.mm;

import org.rcsb.cif.model.*;
import org.rcsb.cif.schema.*;

import javax.annotation.Generated;

/**
 * Data items in the MA_ENERGY_ESTIMATES category record the details of the
 * type of energy estimate calculated for the stored models.
 */
@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class MaEnergyEstimates extends DelegatingCategory {
    public MaEnergyEstimates(Category delegate) {
        super(delegate);
    }

    @Override
    protected Column createDelegate(String columnName, Column column) {
        switch (columnName) {
            case "ordinal_id":
                return getOrdinalId();
            case "energy_type":
                return getEnergyType();
            case "unit":
                return getUnit();
            case "details":
                return getDetails();
            case "data_id":
                return getDataId();
            default:
                return new DelegatingColumn(column);
        }
    }

    /**
     * A unique identifier for the category.
     * @return IntColumn
     */
    public IntColumn getOrdinalId() {
        return delegate.getColumn("ordinal_id", DelegatingIntColumn::new);
    }

    /**
     * Identifies the type of energy estimate.
     * @return StrColumn
     */
    public StrColumn getEnergyType() {
        return delegate.getColumn("energy_type", DelegatingStrColumn::new);
    }

    /**
     * Identifies the unit used to provide the energy.
     * @return StrColumn
     */
    public StrColumn getUnit() {
        return delegate.getColumn("unit", DelegatingStrColumn::new);
    }

    /**
     * Description with any extra information on how the energy is
     * computed (e.g. for full system or only considering some subset).
     * @return StrColumn
     */
    public StrColumn getDetails() {
        return delegate.getColumn("details", DelegatingStrColumn::new);
    }

    /**
     * The data_id identifier corresponding to the energy estimate, if available.
     * This data item is a pointer to _ma_data.id in the MA_DATA category.
     * @return IntColumn
     */
    public IntColumn getDataId() {
        return delegate.getColumn("data_id", DelegatingIntColumn::new);
    }

}