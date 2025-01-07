package com.majesteye.rnd.codex.input;

import com.majesteye.rnd.codex.input.impl.FastaFileInputReader;
import com.majesteye.rnd.codex.input.impl.TextFileInputReader;
import com.majesteye.rnd.codex.input.impl.TsvFileInputReader;

public abstract class InputFileFactory {

    public static InputFileReader getFileReader(String fileName, String format) {
        switch (format) {
            case "text": return new TextFileInputReader(fileName);
            case "fasta": return new FastaFileInputReader(fileName);
            case "tsv": return new TsvFileInputReader(fileName);
            default:
                throw new IllegalArgumentException("Unknown file name: " + fileName);
        }
    }
}
