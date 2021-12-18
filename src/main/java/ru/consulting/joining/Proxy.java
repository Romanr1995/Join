package ru.consulting.joining;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Proxy<E, T extends Joiner<E>> {
    private T join;

    public Proxy(T join) {
        this.join = join;
    }

    public void proxyJoiner(E tA, E tB, String directory, String fileName) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                directory.substring(0, directory.lastIndexOf("\\") + 1) + fileName));
        long start = System.currentTimeMillis();
        join.join(tA, tB, bufferedWriter);
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения join " + tA.getClass().getSimpleName() + " = " + (end - start) + " мс.");
        bufferedWriter.close();
    }
}
