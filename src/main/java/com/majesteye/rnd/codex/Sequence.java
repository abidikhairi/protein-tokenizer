package com.majesteye.rnd.codex;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sequence {

    private String id;

    private String sequence;

    public String toFormattedString() {
        return String.format("%s\t%s", id, sequence);
    }
}
