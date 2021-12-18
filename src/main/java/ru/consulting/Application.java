package ru.consulting;

import ru.consulting.data.Table;
import ru.consulting.joining.ArrayJoiner;
import ru.consulting.joining.HashMapJoiner;
import ru.consulting.joining.LinkedJoiner;
import ru.consulting.joining.Proxy;
import ru.consulting.loading.TableLoader;
import ru.consulting.loading.TableLoaderImpl;

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
            joinerArrayListProxy.proxyJoiner(tablesA, tablesB, fileInputName, "ArrayList.txt");

            Proxy<LinkedList<Table>, LinkedJoiner> joinerLinkedListProxy = new Proxy<>(new LinkedJoiner());
            joinerLinkedListProxy.proxyJoiner(new LinkedList<>(tablesA), new LinkedList<>(tablesB),
                    fileInputName, "LinkedList.txt");

            Proxy<HashMap<Integer, List<String>>, HashMapJoiner> joinerHashMapProxy = new Proxy<>(new HashMapJoiner());
            joinerHashMapProxy.proxyJoiner(HashMapJoiner.sortToHashMap(tablesA), HashMapJoiner.sortToHashMap(tablesB),
                    fileInputName, "ArrayList.txt");

        } else {
            System.out.println("Ошибка!Должно быть задано 2 аргумента.А задано: " + args.length);
        }
    }
}
