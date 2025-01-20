package com.majesteye.rnd.codex.functions;

import com.majesteye.rnd.codex.Sequence;
import com.majesteye.rnd.codex.Vocabulary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenizeSequenceTest {

    @Test
    public void testChunkSequence() {
        TokenizeSequence tokenizer = new TokenizeSequence(6, null);
        String inputSequence = "abcdefghijklmn";

        List<String> tokens = tokenizer.tokenize(inputSequence);

        assertEquals(3, tokens.size());
        assertEquals("abcdef", tokens.get(0));
        assertEquals("mn", tokens.get(tokens.size() - 1));
    }

    @Test
    public void testTokenize() throws IOException {
        Vocabulary vocabulary = new Vocabulary("data/vocab-38k.json");

        TokenizeSequence tokenizer = new TokenizeSequence(6, vocabulary);
        String inputSequence = "GGRGGGEEKKKA";
        Sequence outputSequence = tokenizer.apply(new Sequence(null, inputSequence));

        assertNotNull(outputSequence);
        assertEquals("GGRGGG EEK KKA", outputSequence.getSequence());
    }

}