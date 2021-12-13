package ru.consulting.joining;

import ru.consulting.data.JoiningTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapJoiner implements Joiner<HashMap<Integer, List<String>>> {

    @Override
    public List<JoiningTable> join(HashMap<Integer, List<String>> tableA, HashMap<Integer, List<String>> tableB) {
        List<JoiningTable> joiningTables = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> listEntryTableA : tableA.entrySet()) {
            if (tableB.containsKey(listEntryTableA.getKey())) {
                for (String valueA : listEntryTableA.getValue()) {
                    for (String valueB : tableB.get(listEntryTableA.getKey())) {
                        joiningTables.add(new JoiningTable(listEntryTableA.getKey(), valueA, valueB));
                    }
                }
            }
        }
        return joiningTables;
    }
}
