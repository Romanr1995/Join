package ru.consulting.joining;

import ru.consulting.data.Table;
import ru.consulting.writer.FileWriterImpl;

import java.util.ArrayList;

public class ArrayJoiner implements Joiner<ArrayList<Table>> {

    @Override
    public void join(ArrayList<Table> tableA, ArrayList<Table> tableB, FileWriterImpl writer) {
        writer.getPath().delete();
        writer.writer();
        tableA.stream().parallel().forEach(tA -> tableB.stream().filter(tB -> tA.getId() == tB.getId())
                .forEach(tB -> writer.writer(tA.getId(), tA.getValue(), tB.getValue())));
    }
}
