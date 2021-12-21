package ru.consulting;

import ru.consulting.data.Table;
import ru.consulting.joining.ArrayJoiner;
import ru.consulting.joining.HashMapJoiner;
import ru.consulting.joining.LinkedJoiner;
import ru.consulting.joining.Proxy;
import ru.consulting.loading.TableLoader;
import ru.consulting.loading.TableLoaderImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        String fileInputName = args[0];
        if (args.length == 2) {
            TableLoader tableLoader = new TableLoaderImpl();
            ArrayList<Table> tablesA = tableLoader.loadTables(args[0]);
            ArrayList<Table> tablesB = tableLoader.loadTables(args[1]);

            Proxy<ArrayList<Table>, ArrayJoiner> joinerArrayListProxy = new Proxy<>(new ArrayJoiner());
            BufferedWriter writerToArrayList = new BufferedWriter(new FileWriter(
                    fileInputName.substring(0, fileInputName.lastIndexOf("\\") + 1) + "ArrayList.txt"));
            joinerArrayListProxy.join(tablesA, tablesB, writerToArrayList);

            Proxy<LinkedList<Table>, LinkedJoiner> joinerLinkedListProxy = new Proxy<>(new LinkedJoiner());
            BufferedWriter writerToLinkedList = new BufferedWriter(new FileWriter(
                    fileInputName.substring(0, fileInputName.lastIndexOf("\\") + 1) + "LinkedList.txt"));
            joinerLinkedListProxy.join(new LinkedList<>(tablesA), new LinkedList<>(tablesB),
                    writerToLinkedList);

            Proxy<HashMap<Integer, List<String>>, HashMapJoiner> joinerHashMapProxy = new Proxy<>(new HashMapJoiner());
            BufferedWriter writerToHashMap = new BufferedWriter(new FileWriter(
                    fileInputName.substring(0, fileInputName.lastIndexOf("\\") + 1) + "HashMap.txt"));
            joinerHashMapProxy.join(HashMapJoiner.sortToHashMap(tablesA), HashMapJoiner.sortToHashMap(tablesB),
                    writerToHashMap);

        } else {
            System.out.println("Ошибка!Должно быть задано 2 аргумента.А задано: " + args.length);
        }
    }
}
