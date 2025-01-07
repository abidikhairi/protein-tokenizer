package com.majesteye.rnd.codex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VocabularyTest {

    @Test
    public void  testParseJSONFile() throws Exception {
        Vocabulary vocabulary = new Vocabulary("/run/media/khairi/HIKSEMI/datasets/prot_lang/vocab.json");

        assertEquals(23694, vocabulary.length());
        assertTrue(vocabulary.contains("LL"));
        assertTrue(vocabulary.contains("A"));
        assertTrue(vocabulary.contains("L"));
    }

    @Test
    public void  testTokensConverting() throws Exception {
        Vocabulary vocabulary = new Vocabulary("/run/media/khairi/HIKSEMI/datasets/prot_lang/vocab.json");
        String token = "A";
        Integer tokenId = vocabulary.convertTokenToId(token);

        assertEquals(23694, vocabulary.length());
        assertTrue(vocabulary.contains(token));
        assertEquals(0, tokenId);
    }
}