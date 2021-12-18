package ru.consulting.joining;

import java.io.BufferedWriter;

public interface Joiner<T> {

    void join(T tableA, T tableB, BufferedWriter bufferedWriter);
}
