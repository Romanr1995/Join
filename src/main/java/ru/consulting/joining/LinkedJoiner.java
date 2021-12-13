package ru.consulting.joining;

import ru.consulting.data.JoiningTable;
import ru.consulting.data.Table;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedJoiner implements Joiner<LinkedList<Table>> {

    @Override
    public List<JoiningTable> join(LinkedList<Table> tableA, LinkedList<Table> tableB) {
        List<JoiningTable> joiningTables = new ArrayList<>();

        ListIterator<Table> tableAListIterator = tableA.listIterator();
        ListIterator<Table> tableBListIterator = tableB.listIterator();
        if (tableA.isEmpty() || tableB.isEmpty()) {
            return joiningTables;
        }
        Table nextA = tableAListIterator.next();
        Table nextB = tableBListIterator.next();

        while (tableAListIterator.hasNext() && tableBListIterator.hasNext()) {

            if (nextA.getId() > nextB.getId() && tableBListIterator.hasNext()) {
                nextB = tableBListIterator.next();
            } else if (nextA.getId() < nextB.getId() && tableAListIterator.hasNext()) {
                nextA = tableAListIterator.next();
            } else {
                while (nextA.getId() == nextB.getId()) {
                    ListIterator<Table> tableB2ListIterator = tableB.listIterator(tableBListIterator.previousIndex());
                    Table nextB2 = tableB2ListIterator.next();
                    while (nextA.getId() == nextB2.getId()) {
                        joiningTables.add(new JoiningTable(nextA.getId(), nextA.getValue(), nextB2.getValue()));
                        if (tableB2ListIterator.hasNext()) {
                            nextB2 = tableB2ListIterator.next();
                        } else {
                            break;
                        }
                    }
                    if (tableAListIterator.hasNext()) {
                        nextA = tableAListIterator.next();
                    } else {
                        break;
                    }

                }
            }
        }
        return joiningTables;
    }
}
