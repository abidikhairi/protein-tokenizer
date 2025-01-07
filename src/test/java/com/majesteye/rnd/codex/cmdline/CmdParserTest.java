package com.majesteye.rnd.codex.cmdline;

import com.majesteye.rnd.codex.FileFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdParserTest {


    @Test
    void testParseRequiredArgs() {
        String[] cmdString = {"-inputFile", "sequences.txt", "-v", "vocab.json", "-outputFile", "tokenized-sequences.txt"};
        TokenizerArguments commandLine = CmdParser.parse(cmdString);

        assertNotNull(commandLine);

        assertEquals("sequences.txt", commandLine.getInputFile());
        assertEquals("tokenized-sequences.txt", commandLine.getOutputFile());
        assertEquals("vocab.json", commandLine.getVocabFile());
    }

    @Test
    void testParseSupportedFormats() {
        String[] cmdString = {"-inputFile", "sequences.txt", "-f", "text", "-outputFile", "tokenized-sequences.txt"};
        TokenizerArguments commandLine = CmdParser.parse(cmdString);

        assertNotNull(commandLine);
        assertEquals(FileFormat.text, commandLine.getFileFormat());

        cmdString = new String[]{"-inputFile", "sequences.txt", "-f", "fasta", "-outputFile", "tokenized-sequences.txt"};
        commandLine = CmdParser.parse(cmdString);

        assertNotNull(commandLine);
        assertEquals(FileFormat.fasta, commandLine.getFileFormat());

        // Any other format should fallback to `text`, while printing a warning message
        cmdString = new String[]{"-inputFile", "sequences.txt", "-f", "csv", "-outputFile", "tokenized-sequences.txt"};
        commandLine = CmdParser.parse(cmdString);

        assertNotNull(commandLine);
        assertEquals(FileFormat.text, commandLine.getFileFormat());

    }



}