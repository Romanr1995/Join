package ru.consulting.joining;

import java.io.BufferedWriter;

public class Proxy<E, T extends Joiner<E>> implements Joiner<E> {
    private T join;

    public Proxy(T join) {
        this.join = join;
    }

    @Override
    public void join(E tableA, E tableB, BufferedWriter bufferedWriter) {
        long start = System.currentTimeMillis();
        join.join(tableA, tableB, bufferedWriter);
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения join " + tableA.getClass().getSimpleName() + " = " + (end - start) + " мс.");
    }
//
//    public void proxyJoiner(E tA, E tB, String directory, String fileName) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
//                directory.substring(0, directory.lastIndexOf("\\") + 1) + fileName));
//        long start = System.currentTimeMillis();
//        join.join(tA, tB, bufferedWriter);
//        long end = System.currentTimeMillis();
//        System.out.println("Время выполнения join " + tA.getClass().getSimpleName() + " = " + (end - start) + " мс.");
//    }
}
