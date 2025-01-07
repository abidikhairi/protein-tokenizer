package com.majesteye.rnd.codex.cmdline;

import com.majesteye.rnd.codex.FileFormat;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

public class CmdParser {

    private static final Logger logger = LoggerFactory.getLogger(CmdParser.class);

    private static final Option inputFile = new Option("i", "inputFile", true, "input file");

    private static final Option outputFile = new Option("o", "outputFile", true, "output file");

    private static final Option fileFormat = new Option("f", "fileFormat", true, "file format");

    private static final Option vocabPath = new Option("v", "vocabPath", true, "vocab path (JSON file)");

    private static final Option maxTokenLength = new Option("l", "maxTokenLength", true, "max token length");

    private static final Option help = new Option("h", "help", false, "print help");

    private static final HelpFormatter helpFormatter = new HelpFormatter();

    private static final String[] SUPPORTED_FORMATS = {FileFormat.fasta.name(), FileFormat.text.name(), FileFormat.tsv.name()};

    private static final String MAX_TOKEN_LENGTH = "4";

    public static TokenizerArguments parse(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();

        CmdParser.inputFile.setRequired(true);
        CmdParser.outputFile.setRequired(true);
        CmdParser.vocabPath.setRequired(true);
        CmdParser.maxTokenLength.setConverter(Integer::new);

        Options options = new Options();

        options.addOption(CmdParser.inputFile);
        options.addOption(CmdParser.outputFile);
        options.addOption(CmdParser.vocabPath);
        options.addOption(CmdParser.fileFormat);
        options.addOption(CmdParser.maxTokenLength);
        options.addOption(CmdParser.help);

        logger.info("Parsing command line arguments");
        try {
            CommandLine cmd = commandLineParser.parse(options, args);
            logger.info("Command line arguments parsed successfully");
            String fileFormatOptionValue = cmd.getOptionValue(fileFormat.getOpt(), "text");

            String finalFileFormatOptionValue = fileFormatOptionValue;
            if (Arrays.stream(SUPPORTED_FORMATS).noneMatch(x -> x.equalsIgnoreCase(finalFileFormatOptionValue))) {
                fileFormatOptionValue = "text";
            }

            return TokenizerArguments.builder()
                    .inputFile(cmd.getOptionValue(inputFile.getOpt()))
                    .outputFile(cmd.getOptionValue(outputFile.getOpt()))
                    .vocabFile(cmd.getOptionValue(vocabPath.getOpt()))
                    .maxTokenLength(Integer.parseInt(cmd.getOptionValue(maxTokenLength.getOpt(), MAX_TOKEN_LENGTH)))
                    .fileFormat(FileFormat.valueOf(fileFormatOptionValue))
                    .build();

        } catch (ParseException e) {
            logger.error("Parsing command line arguments failed: {}", e.getMessage());
            helpFormatter.printHelp("protein-tokenizer", options);
            System.exit(1);
        }

        return null; // Unreachable
    }

}
