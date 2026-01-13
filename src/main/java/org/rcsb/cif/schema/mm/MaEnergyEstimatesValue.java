package org.rcsb.cif.schema.mm;

import org.rcsb.cif.model.*;
import org.rcsb.cif.schema.*;

import javax.annotation.Generated;

/**
 * Data items in the MA_ENERGY_ESTIMATES_VALUE category record the values
 * per model or feature for a given energy estimate. Additional
 * data items in the MA_ENERGY_ESTIMATES can be added if needed.
 */
@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class MaEnergyEstimatesValue extends DelegatingCategory {
    public MaEnergyEstimatesValue(Category delegate) {
        super(delegate);
    }

    @Override
    protected Column createDelegate(String columnName, Column column) {
        switch (columnName) {
            case "ordinal_id":
                return getOrdinalId();
            case "energy_estimates_id":
                return getEnergyEstimatesId();
            case "model_id":
                return getModelId();
            case "feature_id":
                return getFeatureId();
            case "numerical_value":
                return getNumericalValue();
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
     * Identifier to the energy estimate.
     * This data item is a pointer to _ma_energy_estimates.ordinal_id in the MA_ENERGY_ESTIMATES category.
     * @return IntColumn
     */
    public IntColumn getEnergyEstimatesId() {
        return delegate.getColumn("energy_estimates_id", DelegatingIntColumn::new);
    }

    /**
     * The identifier for the structural model, for which the energy estimate is provided.
     * This data item is a pointer to _ma_model_list.ordinal_id in the MA_MODEL_LIST category.
     * @return IntColumn
     */
    public IntColumn getModelId() {
        return delegate.getColumn("model_id", DelegatingIntColumn::new);
    }

    /**
     * The identifier for the feature, for which the energy estimate is provided.
     * This data item is a pointer to  _ma_feature_list.feature_id in the MA_FEATURE_LIST category.
     * @return IntColumn
     */
    public IntColumn getFeatureId() {
        return delegate.getColumn("feature_id", DelegatingIntColumn::new);
    }

    /**
     * Numerical value of the energy.
     * @return FloatColumn
     */
    public FloatColumn getNumericalValue() {
        return delegate.getColumn("numerical_value", DelegatingFloatColumn::new);
    }

}