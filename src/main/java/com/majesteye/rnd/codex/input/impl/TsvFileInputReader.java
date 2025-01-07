package com.majesteye.rnd.codex.input.impl;

import com.majesteye.rnd.codex.Sequence;
import com.majesteye.rnd.codex.input.InputFileReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TsvFileInputReader implements InputFileReader {


    private final String inputFile;

    public TsvFileInputReader(String inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public List<Sequence> getSequencesList() throws IOException {
        return Files.lines(Paths.get(inputFile), Charset.defaultCharset())
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .filter(s -> !s.startsWith("Entry"))
                .map(s -> s.split("\t"))
                .map(chunks -> new Sequence(chunks[0], chunks[1]))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Sequence> getSequencesIterator() throws IOException {
        return Files.lines(Paths.get(inputFile), Charset.defaultCharset())
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .filter(s -> !s.startsWith("Entry"))
                .map(s -> s.split("\t"))
                .map(chunks -> new Sequence(chunks[0], chunks[1]))
                .iterator();
    }
}
