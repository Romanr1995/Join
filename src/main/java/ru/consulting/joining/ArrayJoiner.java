package ru.consulting.joining;

import ru.consulting.data.Table;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayJoiner implements Joiner<ArrayList<Table>> {

    @Override
    public void join(ArrayList<Table> tableA, ArrayList<Table> tableB, BufferedWriter bufferedWriter) {

        try {
            bufferedWriter.write("ID\t\tA.VALUE\t\tB.VALUE\n");
            tableA.stream().parallel().forEach(tA -> tableB.stream().filter(tB -> tA.getId() == tB.getId())
                    .forEach(tB -> {
                        try {
                            bufferedWriter.write(tA.getId() + "\t\t" + tA.getValue() + "\t\t" + tB.getValue() + "\n");
                        } catch (IOException e) {
                            System.out.println("Ошибка при join ArrayList.Не удается записать данные в файл");
                        }

                    }));
        } catch (IOException e) {
            System.out.println("Ошибка при join ArrayList.Не удается записать данные в файл");
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии файла.");
            }
        }
    }
}
