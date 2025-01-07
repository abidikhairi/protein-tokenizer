package com.majesteye.rnd.codex;

import com.majesteye.rnd.codex.cmdline.CmdParser;
import com.majesteye.rnd.codex.cmdline.TokenizerArguments;
import com.majesteye.rnd.codex.functions.TokenizeSequence;
import com.majesteye.rnd.codex.input.InputFileFactory;
import com.majesteye.rnd.codex.input.InputFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Tokenizer {

    private static final Logger logger = LoggerFactory.getLogger(Tokenizer.class);

    public static void main(String[] args) throws IOException {
        TokenizerArguments tokenizerArguments = CmdParser.parse(args);

        Integer maxTokenLength = tokenizerArguments.getMaxTokenLength();
        String outputFile = tokenizerArguments.getOutputFile();
        InputFileReader fileReader = InputFileFactory.getFileReader(
                tokenizerArguments.getInputFile(),
                tokenizerArguments.getFileFormat().name()
        );
        Vocabulary vocabulary = new Vocabulary(tokenizerArguments.getVocabFile());

        logger.info("Input file: {}", tokenizerArguments.getInputFile());
        logger.info("File format: {}", tokenizerArguments.getFileFormat());
        logger.info("Using vocabulary: {}", tokenizerArguments.getVocabFile());
        logger.info("Max token length: {}", maxTokenLength);
        logger.info("Output file: {}", outputFile);

        List<Sequence> tokenizedSequences = fileReader.getSequencesList()
                .stream()
                .map(new TokenizeSequence(maxTokenLength, vocabulary))
                .collect(Collectors.toList());

        // Save into output file
        logger.info("Total tokenized sequences: {}", tokenizedSequences.size());
        logger.info("Saving sequences to file: {}", outputFile);

        FileWriter fileWriter = new FileWriter(outputFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        tokenizedSequences
                .forEach(sequence -> printWriter.println(sequence.toFormattedString()));

        printWriter.close();
        fileWriter.close();

        logger.info("Sequences written to: {}", outputFile);
    }
}