package com.majesteye.rnd.codex.input;

import com.majesteye.rnd.codex.Sequence;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface InputFileReader {

    List<Sequence> getSequencesList() throws IOException;

    Iterator<Sequence> getSequencesIterator() throws IOException;
}
