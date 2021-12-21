package ru.consulting;

import ru.consulting.data.Table;
import ru.consulting.joining.ArrayJoiner;
import ru.consulting.joining.HashMapJoiner;
import ru.consulting.joining.LinkedJoiner;
import ru.consulting.joining.Proxy;
import ru.consulting.loading.TableLoader;
import ru.consulting.loading.TableLoaderImpl;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        String directoryName = args[0].substring(0, args[0].lastIndexOf("\\") + 1);
        if (args.length == 2) {
            TableLoader tableLoader = new TableLoaderImpl();
            ArrayList<Table> tablesA = tableLoader.loadTables(args[0]);
            ArrayList<Table> tablesB = tableLoader.loadTables(args[1]);

            Proxy<ArrayList<Table>, ArrayJoiner> joinerArrayListProxy = new Proxy<>(new ArrayJoiner());
            joinerArrayListProxy.join(tablesA, tablesB, directoryName + "ArrayList.txt");

            Proxy<LinkedList<Table>, LinkedJoiner> joinerLinkedListProxy = new Proxy<>(new LinkedJoiner());
            Collections.sort(tablesA);
            Collections.sort(tablesB);
            joinerLinkedListProxy.join(new LinkedList<>(tablesA), new LinkedList<>(tablesB),
                    directoryName + "LinkedList.txt");

            Proxy<HashMap<Integer, List<String>>, HashMapJoiner> joinerHashMapProxy = new Proxy<>(new HashMapJoiner());
            joinerHashMapProxy.join(HashMapJoiner.sortToHashMap(tablesA), HashMapJoiner.sortToHashMap(tablesB),
                    directoryName + "HashMap.txt");
        } else {
            System.out.println("Ошибка!Должно быть задано 2 аргумента.А задано: " + args.length);
        }
    }
}
