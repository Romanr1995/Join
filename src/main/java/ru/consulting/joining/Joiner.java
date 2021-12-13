package ru.consulting.joining;

import ru.consulting.data.JoiningTable;

import java.util.List;

public interface Joiner<T> {

    List<JoiningTable> join(T tableA,T tableB);
}
