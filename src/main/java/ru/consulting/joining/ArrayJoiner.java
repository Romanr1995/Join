package ru.consulting.joining;

import ru.consulting.data.JoiningTable;
import ru.consulting.data.Table;

import java.util.ArrayList;
import java.util.List;

public class ArrayJoiner implements Joiner<ArrayList<Table>> {

    @Override
    public List<JoiningTable> join(ArrayList<Table> tableA, ArrayList<Table> tableB) {

        List<JoiningTable> joiningTables = new ArrayList<>();

        for (Table table1 : tableA) {
            for (Table table2 : tableB) {
                if (table1.getId() == table2.getId()) {
                    joiningTables.add(new JoiningTable(table1.getId(), table1.getValue(), table2.getValue()));
                }
            }
        }
        return joiningTables;
    }
}
