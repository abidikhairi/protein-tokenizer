package com.majesteye.rnd.codex.cmdline;


import com.majesteye.rnd.codex.FileFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TokenizerArguments {

    private String inputFile;

    private String outputFile;

    private String vocabFile;

    private FileFormat fileFormat;

    private Integer maxTokenLength;

}
