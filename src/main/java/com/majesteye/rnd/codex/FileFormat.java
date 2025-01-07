package com.majesteye.rnd.codex;

public enum FileFormat {

    fasta("fasta"),
    text("text"),
    tsv("tsv");

    private final String format;

    FileFormat(String format) {
        this.format = format;
    }
}
