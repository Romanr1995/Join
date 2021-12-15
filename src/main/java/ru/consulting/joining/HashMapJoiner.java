package ru.consulting.joining;

import ru.consulting.writer.FileWriterImpl;

import java.util.HashMap;
import java.util.List;

public class HashMapJoiner implements Joiner<HashMap<Integer, List<String>>> {
    @Override
    public void join(HashMap<Integer, List<String>> tableA, HashMap<Integer, List<String>> tableB, FileWriterImpl writer) {
        writer.getPath().delete();
        writer.writer();
        tableA.forEach((k, v) -> {
            if (tableB.containsKey(k)) {
                v.stream().parallel().forEach(tA -> tableB.get(k).stream().forEach(
                        tB -> writer.writer(k, tA, tB)));
            }
        });
    }
}
