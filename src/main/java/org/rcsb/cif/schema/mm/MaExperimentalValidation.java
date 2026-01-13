package org.rcsb.cif.schema.mm;

import org.rcsb.cif.model.*;
import org.rcsb.cif.schema.*;

import javax.annotation.Generated;

/**
 * Data items in the MA_EXPERIMENTAL_VALIDATION category describe how the existence
 * of the modeled system was experimentally validated, often through multiple
 * complementary experiments. The aim is to provide experimental evidence supporting
 * the biological plausibility of the input, such as entities forming a complex with
 * a specific stoichiometry or designed proteins being able to fold. It can also include
 * negative evidence (e.g. for models used to distinguish positive and negative results).
 * It is not meant to capture experimental data used in modeling (e.g. via spatial restraints).
 */
@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class MaExperimentalValidation extends DelegatingCategory {
    public MaExperimentalValidation(Category delegate) {
        super(delegate);
    }

    @Override
    protected Column createDelegate(String columnName, Column column) {
        switch (columnName) {
            case "ordinal_id":
                return getOrdinalId();
            case "method":
                return getMethod();
            case "type":
                return getType();
            case "value_name":
                return getValueName();
            case "value":
                return getValue();
            case "value_unit":
                return getValueUnit();
            case "positive_outcome":
                return getPositiveOutcome();
            case "reference_type":
                return getReferenceType();
            case "reference_doi":
                return getReferenceDoi();
            case "reference_url":
                return getReferenceUrl();
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
     * Describes the experimental technique used.
     * @return StrColumn
     */
    public StrColumn getMethod() {
        return delegate.getColumn("method", DelegatingStrColumn::new);
    }

    /**
     * The type of evidence.
     * @return StrColumn
     */
    public StrColumn getType() {
        return delegate.getColumn("type", DelegatingStrColumn::new);
    }

    /**
     * The name of experimentally observed value, for example, Kd.
     * @return StrColumn
     */
    public StrColumn getValueName() {
        return delegate.getColumn("value_name", DelegatingStrColumn::new);
    }

    /**
     * The value of the experimental observation.
     * @return FloatColumn
     */
    public FloatColumn getValue() {
        return delegate.getColumn("value", DelegatingFloatColumn::new);
    }

    /**
     * Units for the experimental observation value.
     * @return StrColumn
     */
    public StrColumn getValueUnit() {
        return delegate.getColumn("value_unit", DelegatingStrColumn::new);
    }

    /**
     * Indicates the outcome or result of a particular experimental validation.
     * This entry specifies whether the validation step was successful (YES)
     * or unsuccessful (NO).
     * @return StrColumn
     */
    public StrColumn getPositiveOutcome() {
        return delegate.getColumn("positive_outcome", DelegatingStrColumn::new);
    }

    /**
     * The type of reference for the experimental validation.
     * @return StrColumn
     */
    public StrColumn getReferenceType() {
        return delegate.getColumn("reference_type", DelegatingStrColumn::new);
    }

    /**
     * The Digital Object Identifier (DOI) for the experimental validation information.
     * If available, reference_doi should be provided instead of reference_url.
     * @return StrColumn
     */
    public StrColumn getReferenceDoi() {
        return delegate.getColumn("reference_doi", DelegatingStrColumn::new);
    }

    /**
     * URL linking to the experimental validation information.
     * @return StrColumn
     */
    public StrColumn getReferenceUrl() {
        return delegate.getColumn("reference_url", DelegatingStrColumn::new);
    }

    /**
     * Details about the validation process and the data availability.
     * @return StrColumn
     */
    public StrColumn getDetails() {
        return delegate.getColumn("details", DelegatingStrColumn::new);
    }

    /**
     * The data_id identifier corresponding to the experimental validation, if available.
     * This data item is a pointer to _ma_data.id in the MA_DATA category.
     * @return IntColumn
     */
    public IntColumn getDataId() {
        return delegate.getColumn("data_id", DelegatingIntColumn::new);
    }

}