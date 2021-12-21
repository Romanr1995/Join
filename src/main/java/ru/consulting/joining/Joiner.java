package ru.consulting.joining;

import java.io.BufferedWriter;
import java.io.IOException;

public interface Joiner<T> {

    void join(T tableA, T tableB, BufferedWriter bufferedWriter) throws IOException;
}
