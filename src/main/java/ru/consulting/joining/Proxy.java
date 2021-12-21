package ru.consulting.joining;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Proxy<E, T extends Joiner<E>> {
    private T join;

    public Proxy(T join) {
        this.join = join;
    }

    public void join(E tableA, E tableB, String fileName) {
        String className = tableA.getClass().getSimpleName();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            long start = System.currentTimeMillis();
            join.join(tableA, tableB, bufferedWriter);
            long end = System.currentTimeMillis();
            System.out.println("Время выполнения join " + className + " = " + (end - start) + " мс.");
        } catch (IOException ioException) {
            System.out.println("Ошибка при join " + className + ".Не удается записать данные в файл");
        }
    }
}
