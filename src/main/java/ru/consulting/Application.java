package ru.consulting;

import ru.consulting.data.Table;
import ru.consulting.joining.ArrayJoiner;
import ru.consulting.joining.HashMapJoiner;
import ru.consulting.joining.Joiner;
import ru.consulting.joining.LinkedJoiner;
import ru.consulting.loading.TableLoader;
import ru.consulting.loading.TableLoaderImpl;
import ru.consulting.writer.FileWrite;
import ru.consulting.writer.FileWriterImpl;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        if (args.length == 5) {
            TableLoader tableLoader = new TableLoaderImpl();
            ArrayList<Table> tablesA = tableLoader.loadTables(args[0]);
            ArrayList<Table> tablesB = tableLoader.loadTables(args[1]);
            Joiner<ArrayList<Table>> arrayListJoiner = new ArrayJoiner();


            Collections.sort(tablesA);
            Collections.sort(tablesB);
            LinkedList<Table> linkedTablesA = new LinkedList<>(tablesA);
            LinkedList<Table> linkedTableB = new LinkedList<>(tablesB);
            Joiner<LinkedList<Table>> linkedListJoiner = new LinkedJoiner();


            HashMap<Integer, List<String>> valuesFromTableAGroupId = (HashMap<Integer, List<String>>) tablesA.stream()
                    .collect(Collectors.groupingBy(Table::getId, Collectors.mapping(
                            Table::getValue, Collectors.toList())));
            HashMap<Integer, List<String>> valuesFromTableBGroupId = (HashMap<Integer, List<String>>) tablesB.stream()
                    .collect(Collectors.groupingBy(Table::getId, Collectors.mapping(
                            Table::getValue, Collectors.toList())));
            Joiner<HashMap<Integer, List<String>>> hashMapJoiner = new HashMapJoiner();


            FileWrite fileWrite1 = new FileWriterImpl();
            FileWrite fileWrite2 = new FileWriterImpl(args[0]);
            if (Path.of(args[2]).getParent() == null) {
                fileWrite2.writer(arrayListJoiner.join(tablesA, tablesB), args[2]);
            } else {
                fileWrite1.writer(arrayListJoiner.join(tablesA, tablesB), args[2]);
            }

            if (Path.of(args[3]).getParent() == null) {
                fileWrite2.writer(linkedListJoiner.join(linkedTablesA, linkedTableB), args[3]);
            } else {
                fileWrite1.writer(linkedListJoiner.join(linkedTablesA, linkedTableB), args[3]);
            }

            if (Path.of(args[4]).getParent() == null) {
                fileWrite2.writer(hashMapJoiner.join(valuesFromTableAGroupId, valuesFromTableBGroupId), args[4]);
            } else {
                fileWrite1.writer(linkedListJoiner.join(linkedTablesA, linkedTableB), args[4]);
            }
        } else {
            System.out.println("Ошибка!Должно быть задано 5 аргументов.А задано: " + args.length);
        }
    }
}
