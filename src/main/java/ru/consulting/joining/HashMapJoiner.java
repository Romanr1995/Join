package ru.consulting.joining;

import ru.consulting.data.Table;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashMapJoiner implements Joiner<HashMap<Integer, List<String>>> {
    @Override
    public void join(HashMap<Integer, List<String>> tableA, HashMap<Integer, List<String>> tableB, BufferedWriter bufferedWriter) {

        try {
            bufferedWriter.write("ID\t\tA.VALUE\t\tB.VALUE\n");
            tableA.forEach((k, v) -> {
                if (tableB.containsKey(k)) {
                    v.stream().parallel().forEach(tA -> tableB.get(k).stream().forEach(
                            tB -> {
                                try {
                                    bufferedWriter.write(k + "\t\t" + tA + "\t\t" + tB + "\n");
                                } catch (IOException e) {
                                    System.out.println("Ошибка при join HastMap.Не удается записать данные в файл");
                                }
                            }));
                }
            });
        } catch (IOException e) {
            System.out.println("Ошибка при join HashMap.Не удается записать данные в файл");
        }
    }

    public static HashMap<Integer, List<String>> sortToHashMap(List<Table> tables) {
        return (HashMap<Integer, List<String>>) tables.stream()
                .collect(Collectors.groupingBy(Table::getId, Collectors.mapping(
                        Table::getValue, Collectors.toList())));
    }
}
