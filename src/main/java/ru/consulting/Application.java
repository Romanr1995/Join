package ru.consulting;

import ru.consulting.data.Table;
import ru.consulting.joining.ArrayJoiner;
import ru.consulting.joining.HashMapJoiner;
import ru.consulting.joining.Joiner;
import ru.consulting.joining.LinkedJoiner;
import ru.consulting.loading.TableLoader;
import ru.consulting.loading.TableLoaderImpl;
import ru.consulting.writer.FileWriterImpl;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        if (args.length == 2) {
            TableLoader tableLoader = new TableLoaderImpl();
            ArrayList<Table> tablesA = tableLoader.loadTables(args[0]);
            ArrayList<Table> tablesB = tableLoader.loadTables(args[1]);
            Joiner<ArrayList<Table>> arrayListJoiner = new ArrayJoiner();

            LinkedList<Table> linkedTablesA = new LinkedList<>(tablesA);
            LinkedList<Table> linkedTableB = new LinkedList<>(tablesB);
            Collections.sort(linkedTablesA);
            Collections.sort(linkedTableB);
            Joiner<LinkedList<Table>> linkedListJoiner = new LinkedJoiner();


            HashMap<Integer, List<String>> valuesFromTableAGroupId = (HashMap<Integer, List<String>>) tablesA.stream()
                    .collect(Collectors.groupingBy(Table::getId, Collectors.mapping(
                            Table::getValue, Collectors.toList())));
            HashMap<Integer, List<String>> valuesFromTableBGroupId = (HashMap<Integer, List<String>>) tablesB.stream()
                    .collect(Collectors.groupingBy(Table::getId, Collectors.mapping(
                            Table::getValue, Collectors.toList())));
            Joiner<HashMap<Integer, List<String>>> hashMapJoiner = new HashMapJoiner();

            String directory = args[0];
            FileWriterImpl fileWriteArrayList = new FileWriterImpl(directory, "ArrayList.txt");
            FileWriterImpl fileWriteLinkedList = new FileWriterImpl(directory, "LinkedList.txt");
            FileWriterImpl fileWriteHashMap = new FileWriterImpl(directory, "HashMap.txt");

            arrayListJoiner.join(tablesA, tablesB, fileWriteArrayList);
            linkedListJoiner.join(linkedTablesA, linkedTableB, fileWriteLinkedList);
            hashMapJoiner.join(valuesFromTableAGroupId, valuesFromTableBGroupId, fileWriteHashMap);

        } else {
            System.out.println("Ошибка!Должно быть задано 2 аргумента.А задано: " + args.length);
        }
    }
}
