package org.rcsb.cif;

import org.rcsb.cif.model.CifFile;
import org.rcsb.cif.model.FloatColumn;
import org.rcsb.cif.schema.StandardSchemata;
import org.rcsb.cif.schema.mm.AtomSite;
import org.rcsb.cif.schema.mm.MmCifBlock;
import org.rcsb.cif.schema.mm.MmCifFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

class Demo {
    public static void main(String[] args) throws IOException {
        parseFile();
        System.out.println();
        buildModel();
        System.out.println();
        convertBinaryCIF();
        System.out.println();
        convertIHM();
        System.out.println();
        convertAlphaFold();
    }

    private static void parseFile() throws IOException {
        String pdbId = "3J3Q";
        boolean parseBinary = true;

        // CIF and BinaryCIF are stored in the same data structure
        // to access the data, it does not matter where and in which format the data came from
        // all relevant IO operations are exposed by the CifIO class
        CifFile cifFile;
        if (parseBinary) {
            // parse binary CIF from RCSB PDB
            cifFile = CifIO.readFromURL(new URL("https://models.rcsb.org/" + pdbId + ".bcif"));
        } else {
            // parse CIF from RCSB PDB
            cifFile = CifIO.readFromURL(new URL("https://files.rcsb.org/download/" + pdbId + ".cif"));
        }
        // fine-grained options are available in the CifOptions class

        // access can be generic or using a specified schema - currently supports MMCIF and CIF_CORE
        // you can even use a custom dictionary
        MmCifFile mmCifFile = cifFile.as(StandardSchemata.MMCIF);

        // get first block of CIF
        MmCifBlock data = mmCifFile.getFirstBlock();

        // get category with name '_atom_site' from first block - access is type-safe, all categories
        // are inferred from the CIF schema
        AtomSite atomSite = data.getAtomSite();
        FloatColumn cartnX = atomSite.getCartnX();

        // obtain entry id
        String entryId = data.getEntry().getId().get(0);
        System.out.println(entryId);

        // calculate the average x-coordinate - #values() returns as DoubleStream as defined by the
        // schema for column 'Cartn_x'
        OptionalDouble averageCartnX = cartnX.values().average();
        averageCartnX.ifPresent(System.out::println);

        // print the last residue sequence id - this time #values() returns an IntStream
        OptionalInt lastLabelSeqId = atomSite.getLabelSeqId().values().max();
        lastLabelSeqId.ifPresent(System.out::println);

        // print record type - or #values() may be text
        Optional<String> groupPdb = data.getAtomSite().getGroupPDB().values().findFirst();
        groupPdb.ifPresent(System.out::println);
    }

    private static void buildModel() throws IOException {
        // all builder functionality is exposed by the CifBuilder class
        // again access can be generic or following a given schema
        MmCifFile cifFile = CifBuilder.enterFile(StandardSchemata.MMCIF)
                // create a block
                .enterBlock("1EXP")
                // create a category with name 'entry'
                .enterEntry()
                // set value of column 'id'
                .enterId()
                // to '1EXP'
                .add("1EXP")
                // leave current column
                .leaveColumn()
                // and category
                .leaveCategory()

                // create atom site category
                .enterAtomSite()
                // and specify some x-coordinates
                .enterCartnX()
                .add(1.0, -2.4, 4.5)
                // values can be unknown or not specified
                .markNextUnknown()
                .add(-3.14, 5.0)
                .leaveColumn()

                // after leaving, the builder is in AtomSite again and provides column names
                .enterCartnY()
                .add(0.0, -1.0, 2.72)
                .markNextNotPresent()
                .add(42, 100)
                .leaveColumn()

                // leaving the builder will release the CifFile instance
                .leaveCategory()
                .leaveBlock()
                .leaveFile();

        // the created CifFile instance behaves like a parsed file and can be processed or written as needed
        System.out.println(new String(CifIO.writeText(cifFile)));

        System.out.println(cifFile.getFirstBlock().getEntry().getId().get(0));
        cifFile.getFirstBlock()
                .getAtomSite()
                .getCartnX()
                .values()
                .forEach(System.out::println);
    }

    private static void convertBinaryCIF() throws IOException {
        long start = System.currentTimeMillis();

        String[] s = {"4HHB", "2LGI", "3HQV", "7ART", "3J3Q", "4BTS", "11BJ", "11HB", "10DK", "12GB", "11TG", "9O5G", "9QW5", "6VC1", "3PDM", "2XKM", "9HH6", "3IFX", "1RMN", "1SSZ", "2BVK", "1UR6"};

        for(String id : s) {
            CifFile cifFile = CifIO.readFromURL(new URL("https://files.rcsb.org/download/" + id + ".cif"));
        MmCifFile mmCifFile = cifFile.as(StandardSchemata.MMCIF);

        // print average quality score
        /*System.out.println(mmCifFile.getFirstBlock()
                .getMaQaMetricLocal()
                .getMetricValue()
                .values()
                .average()
                .orElseThrow());*/

        // convert to BinaryCIF representation
        byte[] output = CifIO.writeBinary(mmCifFile);

    Files.write(Path.of(id + "_javaCopy.bcif"), output);
    
    long end = System.currentTimeMillis();
    long runtime = end - start;
    System.out.println("Total runtime: " + runtime + " ms");

    System.out.println("BinaryCIF file written to: " + id + "_javaCopy.bcif");

    start = System.currentTimeMillis();
        }

        
    }


    private static void convertIHM() throws IOException {
        long start = System.currentTimeMillis();

        String[] s = {"9A8K", "9AAO", "8ZZ1", "8ZZI", "9A01", "9A8M", "9A1G"};

        for(String id : s) {
            CifFile cifFile = CifIO.readFromURL(new URL("https://files.rcsb.org/download/" + id + ".cif"));
        MmCifFile mmCifFile = cifFile.as(StandardSchemata.MMCIF);

        // print average quality score
        /*System.out.println(mmCifFile.getFirstBlock()
                .getMaQaMetricLocal()
                .getMetricValue()
                .values()
                .average()
                .orElseThrow());*/

        // convert to BinaryCIF representation
        byte[] output = CifIO.writeBinary(mmCifFile);

    Files.write(Path.of(id + "_javaCopy.bcif"), output);
    
    long end = System.currentTimeMillis();
    long runtime = end - start;
    System.out.println("Total runtime: " + runtime + " ms");

    System.out.println("IHM BinaryCIF file written to: " + id + "_javaCopy.bcif");

    start = System.currentTimeMillis();
        }

        
    }


    private static void convertAlphaFold() throws IOException {
        long start = System.currentTimeMillis();

        String[] s = {"AF_AFA0A017SEY2F1", "AF_AFA0A017SQ41F1", "MA_MABAKCEPC0001", "MA_MAASFVASFVG001", "MA_MAORNLSPHDIV00001", "MA_MAT3VR3003", "MA_MACOFFESLACC100000G1I1"};

        for(String id : s) {
            CifFile cifFile = CifIO.readFromURL(new URL("https://alphafold.ebi.ac.uk/files/" + id + "-F1-model_v6.cif"));
        MmCifFile mmCifFile = cifFile.as(StandardSchemata.MMCIF);

        // print average quality score
        /*System.out.println(mmCifFile.getFirstBlock()
                .getMaQaMetricLocal()
                .getMetricValue()
                .values()
                .average()
                .orElseThrow());*/

        // convert to BinaryCIF representation
        byte[] output = CifIO.writeBinary(mmCifFile);

    Files.write(Path.of(id + "_javaCopy.bcif"), output);
    
    long end = System.currentTimeMillis();
    long runtime = end - start;
    System.out.println("Total runtime: " + runtime + " ms");

    System.out.println("AlphaFold BinaryCIF file written to: " + id + "_javaCopy.bcif");

    start = System.currentTimeMillis();
        }

        
    }
}
