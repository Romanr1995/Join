package ru.consulting.joining;

import ru.consulting.data.Table;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedJoiner implements Joiner<LinkedList<Table>> {
    @Override
    public void join(LinkedList<Table> tableA, LinkedList<Table> tableB, BufferedWriter bufferedWriter) throws IOException {
        ListIterator<Table> tableAListIterator = tableA.listIterator();
        ListIterator<Table> tableBListIterator = tableB.listIterator();
        Table nextA = tableAListIterator.next();
        Table nextB = tableBListIterator.next();

        bufferedWriter.write("ID\t\tA.VALUE\t\tB.VALUE\n");
        while (true) {
            if (nextA.getId() > nextB.getId() && tableBListIterator.hasNext()) {
                nextB = tableBListIterator.next();
            } else if (nextA.getId() < nextB.getId() && tableAListIterator.hasNext()) {
                nextA = tableAListIterator.next();
            } else {
                int stepsCounter = 0;
                while (nextA.getId() == nextB.getId()) {
                    bufferedWriter.write(nextA.getId() + "\t\t" + nextA.getValue() + "\t\t" +
                            nextB.getValue() + "\n");
                    stepsCounter++;
                    if (tableBListIterator.hasNext()) {
                        nextB = tableBListIterator.next();
                    } else {
                        break;
                    }
                }
                if (stepsCounter > 0) {
                    for (int i = 0; i <= stepsCounter; i++) {
                        tableBListIterator.previous();
                    }
                    nextB = tableBListIterator.next();
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
