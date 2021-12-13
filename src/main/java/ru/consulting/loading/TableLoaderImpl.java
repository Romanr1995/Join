package ru.consulting.loading;

import ru.consulting.data.Table;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;

public class TableLoaderImpl implements TableLoader {

    @Override
    public ArrayList<Table> loadTables(String path) {

        ArrayList<Table> tables = new ArrayList<>();
        String fileName = Path.of(path).getFileName().toString();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            int countLine = 0;

            while ((line = bufferedReader.readLine()) != null) {
                countLine++;
                String[] lineTable = line.split(",");
                if (lineTable.length == 2) {
                    try {
                        tables.add(new Table(Integer.parseInt(lineTable[0].trim()), lineTable[1]));
                    } catch (NumberFormatException e) {
                        if (lineTable[0].isEmpty()) {
                            System.out.println("Файл с именем: " + fileName +
                                    ",в строке " + countLine + " значение ID не должно быть пустым.\n");
                        } else {
                            System.out.println("Ошибка в строке " + countLine + " файла: " + fileName +
                                    ".Значение ID = " + lineTable[0] + " недопустимо.\n");
                        }
                    }
                } else {
                    System.out.println("В строке " + countLine + " файла: " + fileName + " не верно задано количество аргуентов." +
                            "Должно быть 2 аргумента,перечисленных через ,\n");
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Ошибка!Файл с именем " + path + " не найден!");
        } catch (IOException exception) {
            System.out.println("При чтении данных из файла " + path + " произошла ошибка.");
        }
        return tables;
    }
}
