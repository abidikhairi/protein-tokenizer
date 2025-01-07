package com.majesteye.rnd.codex.input.impl;

import com.majesteye.rnd.codex.Sequence;
import com.majesteye.rnd.codex.input.InputFileReader;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompoundSet;
import org.biojava.nbio.core.sequence.io.FastaReader;
import org.biojava.nbio.core.sequence.io.GenericFastaHeaderParser;
import org.biojava.nbio.core.sequence.io.ProteinSequenceCreator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FastaFileInputReader implements InputFileReader {

    private final String inputFile;

    public FastaFileInputReader(String inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public List<Sequence> getSequencesList() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.inputFile);

        FastaReader<ProteinSequence, AminoAcidCompound> fastaReader = new FastaReader<>(fileInputStream,
                new GenericFastaHeaderParser<>(),
                new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet()));

        return fastaReader.process()
                .values()
                .stream()
                .map(proteinSequence -> new Sequence(proteinSequence.getAccession().getID(), proteinSequence.getSequenceAsString()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Sequence> getSequencesIterator() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.inputFile);

        FastaReader<ProteinSequence, AminoAcidCompound> fastaReader = new FastaReader<>(fileInputStream,
                new GenericFastaHeaderParser<>(),
                new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet()));

        return fastaReader.process()
                .values()
                .stream()
                .map(proteinSequence -> new Sequence(proteinSequence.getAccession().getID(), proteinSequence.getSequenceAsString()))
                .iterator();
    }
}
