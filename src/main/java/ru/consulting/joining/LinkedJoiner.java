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
                        writer.writer(nextA.getId(), nextA.getValue(), nextB2.getValue());
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
    }
}
