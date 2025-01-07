package com.majesteye.rnd.codex;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Vocabulary {

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Pair<K, V> {
        private K key;
        private V value;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Map<Integer, String> id2Token;
    private final Map<String, Integer> token2Id;

    public Vocabulary(String vocabFile) throws IOException {
        this.token2Id = objectMapper.readValue(new File(vocabFile), new TypeReference<Map<String, Integer>>() { });
        this.id2Token = this.token2Id.entrySet()
                .stream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toMap(Pair::getValue, Pair::getKey));
    }

    public boolean contains(String token) {
        return token2Id.containsKey(token);
    }

    public Integer convertTokenToId(String token) {
        return token2Id.get(token);
    }

    public String convertIdToToken(Integer id) {
        return this.id2Token.get(id);
    }

    public Integer length() {
        return this.token2Id.size();
    }
}
