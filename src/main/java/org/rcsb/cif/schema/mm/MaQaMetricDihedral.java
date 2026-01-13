package org.rcsb.cif.schema.mm;

import org.rcsb.cif.model.*;
import org.rcsb.cif.schema.*;

import javax.annotation.Generated;

/**
 * Data items in the MA_QA_METRIC_DIHEDRAL category capture the analysis of
 * individual torsion angles of a molecule. This analysis may involve dihedral
 * scanning using quantum-mechanical calculations, or a comparison of torsion
 * angle values with probability distributions obtained from experimental data
 * (e.g. TorsionAnalyzer). Here, the main output is not the value from the
 * analysis, but rather a quality classification resulting from an assessment
 * of the energetic state or likelihood of each dihedral angle.
 */
@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class MaQaMetricDihedral extends DelegatingCategory {
    public MaQaMetricDihedral(Category delegate) {
        super(delegate);
    }

    @Override
    protected Column createDelegate(String columnName, Column column) {
        switch (columnName) {
            case "ordinal_id":
                return getOrdinalId();
            case "atom_id_1":
                return getAtomId1();
            case "atom_id_2":
                return getAtomId2();
            case "atom_id_3":
                return getAtomId3();
            case "atom_id_4":
                return getAtomId4();
            case "metric_id":
                return getMetricId();
            case "metric_value":
                return getMetricValue();
            case "quality":
                return getQuality();
            case "smarts_pattern":
                return getSmartsPattern();
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
     * The identifier for one of the four atoms that define the dihedral angle.
     * This one is the outer atom connected to atom_id_2.
     * This data item is a pointer to _atom_site.id in the ATOM_SITE category.
     * @return StrColumn
     */
    public StrColumn getAtomId1() {
        return delegate.getColumn("atom_id_1", DelegatingStrColumn::new);
    }

    /**
     * The identifier for one of the four atoms that define the dihedral angle.
     * This one is the inner atom connected to atom_id_1 and atom_id_3.
     * This data item is a pointer to _atom_site.id in the ATOM_SITE category.
     * @return StrColumn
     */
    public StrColumn getAtomId2() {
        return delegate.getColumn("atom_id_2", DelegatingStrColumn::new);
    }

    /**
     * The identifier for one of the four atoms that define the dihedral angle.
     * This one is the inner atom connected to atom_id_2 and atom_id_4.
     * This data item is a pointer to _atom_site.id in the ATOM_SITE category.
     * @return StrColumn
     */
    public StrColumn getAtomId3() {
        return delegate.getColumn("atom_id_3", DelegatingStrColumn::new);
    }

    /**
     * The identifier for one of the four atoms that define the dihedral angle.
     * This one is the outer atom connected to atom_id_3.
     * This data item is a pointer to _atom_site.id in the ATOM_SITE category.
     * @return StrColumn
     */
    public StrColumn getAtomId4() {
        return delegate.getColumn("atom_id_4", DelegatingStrColumn::new);
    }

    /**
     * The identifier for the QA metric.
     * This data item is a pointer to _ma_qa_metric.id in the MA_QA_METRIC category.
     * @return IntColumn
     */
    public IntColumn getMetricId() {
        return delegate.getColumn("metric_id", DelegatingIntColumn::new);
    }

    /**
     * Computed or measured value used for the quality metric.
     * The type of the value is implicitly determined by the method used in the
     * validation (e.g. dihedral value, QM energy of dihedral above lowest energy value).
     * @return FloatColumn
     */
    public FloatColumn getMetricValue() {
        return delegate.getColumn("metric_value", DelegatingFloatColumn::new);
    }

    /**
     * Indicates the outcome or result of the analysis. For instance a quality
     * classification into 'relaxed', 'tolerable' or 'strained' as used by TorsionAnalyzer.
     * @return StrColumn
     */
    public StrColumn getQuality() {
        return delegate.getColumn("quality", DelegatingStrColumn::new);
    }

    /**
     * Option 1: SMARTS pattern that specifies the dihedral angle in its chemical environment,
     * encoding the observed frequency distribution for validation against the torsion angle in
     * the deposited structure model. Option 2: SMARTS pattern that defines the fragment used
     * for dihedral scanning.
     * @return StrColumn
     */
    public StrColumn getSmartsPattern() {
        return delegate.getColumn("smarts_pattern", DelegatingStrColumn::new);
    }

}