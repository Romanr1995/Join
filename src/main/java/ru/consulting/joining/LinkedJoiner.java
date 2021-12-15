package ru.consulting.joining;

import ru.consulting.data.Table;
import ru.consulting.writer.FileWriterImpl;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedJoiner implements Joiner<LinkedList<Table>> {
    @Override
    public void join(LinkedList<Table> tableA, LinkedList<Table> tableB, FileWriterImpl writer) {
        ListIterator<Table> tableAListIterator = tableA.listIterator();
        ListIterator<Table> tableBListIterator = tableB.listIterator();
        Table nextA = tableAListIterator.next();
        Table nextB = tableBListIterator.next();

        writer.getPath().delete();
        writer.writer();
        while (true) {
            if (nextA.getId() > nextB.getId() && tableBListIterator.hasNext()) {
                nextB = tableBListIterator.next();
            } else if (nextA.getId() < nextB.getId() && tableAListIterator.hasNext()) {
                nextA = tableAListIterator.next();
            } else {
                while (nextA.getId() == nextB.getId()) {
                    writer.writer(nextA.getId(), nextA.getValue(), nextB.getValue());
                    if (tableBListIterator.hasNext()) {
                        nextB = tableBListIterator.next();
                    } else {
                        break;
                    }
                }
                while (tableBListIterator.hasPrevious()) {
                    nextB = tableBListIterator.previous();
                }
                nextB = tableBListIterator.next();
                if (tableAListIterator.hasNext()) {
                    nextA = tableAListIterator.next();
                } else {
                    break;
                }
            }
        }
    }
}
