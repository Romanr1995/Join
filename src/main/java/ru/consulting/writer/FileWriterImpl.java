package ru.consulting.writer;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWriterImpl {
    private String directory;
    private String nameFile;
    private File path;

    public FileWriterImpl(String directory, String nameFile) {
        this.directory = directory;
        this.nameFile = nameFile;
        path = new File(directory.substring(0, directory.lastIndexOf("\\") + 1) + nameFile);
    }

    public void writer(int id, String valueTableA, String valueTableB) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path, true), StandardCharsets.UTF_8))) {

            String formatWrite = String.format("%d\t\t%s\t\t%s\n", id, valueTableA,
                    valueTableB);
            bufferedWriter.write(formatWrite);

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Файл " + path + " не найден.");
        } catch (IOException exception) {
            System.out.println("При записи данных в файл произошла ошибка.");
        }
    }

    public void writer() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path, true), StandardCharsets.UTF_8))) {

            bufferedWriter.write("ID\t\tA.VALUE\t\tB.VALUE\n");

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Файл " + path + " не найден.");
        } catch (IOException exception) {
            System.out.println("При записи данных в файл произошла ошибка.");
        }
    }

    public void cleanFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            bufferedWriter.write("");

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Файл " + path + " не найден.");
        } catch (IOException exception) {
            System.out.println("При записи данных в файл произошла ошибка.");
        }
    }

    public File getPath() {
        return path;
    }

}
