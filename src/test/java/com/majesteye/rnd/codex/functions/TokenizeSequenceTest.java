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
        TokenizeSequence tokenizer = new TokenizeSequence(4, null);
        String inputSequence = "abcdefghijklmn";

        List<String> tokens = tokenizer.tokenize(inputSequence);

        assertEquals(4, tokens.size());
        assertEquals("abcd", tokens.get(0));
        assertEquals("mn", tokens.get(tokens.size() - 1));
    }

    @Test
    public void testTokenize() throws IOException {
        Vocabulary vocabulary = new Vocabulary("/run/media/khairi/HIKSEMI/datasets/prot_lang/vocab.json");

        TokenizeSequence tokenizer = new TokenizeSequence(4, vocabulary);
        String inputSequence = "ABCDEFGHIKLMN";
        Sequence outputSequence = tokenizer.apply(new Sequence(null, inputSequence));

        assertNotNull(outputSequence);
        assertEquals("A B C D EFG H IKL M N", outputSequence.getSequence());
    }

}