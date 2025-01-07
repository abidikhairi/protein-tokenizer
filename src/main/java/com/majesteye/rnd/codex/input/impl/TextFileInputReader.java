package com.majesteye.rnd.codex.input.impl;

import com.majesteye.rnd.codex.Sequence;
import com.majesteye.rnd.codex.input.InputFileReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TextFileInputReader implements InputFileReader {

    private final String inputFile;

    public TextFileInputReader(String inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public List<Sequence> getSequencesList() throws IOException {
        AtomicInteger index = new AtomicInteger();
        return Files.lines(Paths.get(inputFile), Charset.defaultCharset())
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> new Sequence(String.valueOf(index.incrementAndGet()), s))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Sequence> getSequencesIterator() throws IOException {
        return Files.lines(Paths.get(inputFile), Charset.defaultCharset())
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> new Sequence(null, s))
                .iterator();
    }
}
