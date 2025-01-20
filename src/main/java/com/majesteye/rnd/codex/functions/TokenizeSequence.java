package com.majesteye.rnd.codex.functions;

import com.majesteye.rnd.codex.Sequence;
import com.majesteye.rnd.codex.Vocabulary;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TokenizeSequence implements Function<Sequence, Sequence> {

    private final Integer maxTokenLength;
    private final Vocabulary vocabulary;

    public TokenizeSequence(Integer maxTokenLength, Vocabulary vocabulary) {
        super();
        this.maxTokenLength = maxTokenLength;
        this.vocabulary = vocabulary;
    }

    @Override
    public Sequence apply(Sequence sequence) {
        List<String> tokens = tokenize(sequence.getSequence());
        List<String> result = new ArrayList<>();

        for (String token : tokens) {
            List<String> strings = this.doLookup(token, Math.min(token.length(), this.maxTokenLength));

            result.addAll(strings);
        }

        return new Sequence(sequence.getId(), String.join(" ", result));
    }

    private List<String> doLookup(String token, Integer tokenSize) {
        if (tokenSize == 1 || token.length() == 1) {
            return Collections.singletonList(token);
        }
        if (vocabulary.contains(token)) {
            return Collections.singletonList(token);
        } else {
            int mid = token.length() / 2;
            String left = token.substring(0, mid);
            String right = token.substring(mid);

            List<String> result = new ArrayList<>();
            result.addAll(doLookup(left, left.length()));
            result.addAll(doLookup(right, right.length()));
            return result;
        }
    }

    public List<String> tokenize(String inputSequence) {
        ArrayList<String> tokens = new ArrayList<>();

        for (int i = 0; i < inputSequence.length(); i += this.maxTokenLength) {
            tokens.add(inputSequence.substring(i, Math.min(i + this.maxTokenLength, inputSequence.length())));
        }

        return tokens;
    }
}
