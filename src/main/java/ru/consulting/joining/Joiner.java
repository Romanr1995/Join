package ru.consulting.joining;

import ru.consulting.writer.FileWriterImpl;

import java.io.IOException;

public interface Joiner<T> {

    void join(T tableA, T tableB, FileWriterImpl fileWrite);
}
