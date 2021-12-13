package ru.consulting.writer;

import ru.consulting.data.JoiningTable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class FileWriterImpl implements FileWrite {
    private String directory;

    public FileWriterImpl() {

    }

    public FileWriterImpl(String directory) {
        this.directory = directory;
    }

    @Override
    public void writer(List<JoiningTable> joiningTables, String path) {
        if (directory != null) {
            path = directory.substring(0, directory.lastIndexOf("\\") + 1) + path;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {

            bufferedWriter.write("ID\t\tA.VALUE\t\tB.VALUE\n");
            for (JoiningTable joiningTable : joiningTables) {
                String formatWrite = String.format("%d\t\t%s\t\t%s\n", joiningTable.getId(), joiningTable.getValueTableA(),
                        joiningTable.getValueTableB());
                bufferedWriter.write(formatWrite);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Файл " + path + " не найден.");
        } catch (IOException exception) {
            System.out.println("При записи данных в файл произошла ошибка.");
        }
    }
}
