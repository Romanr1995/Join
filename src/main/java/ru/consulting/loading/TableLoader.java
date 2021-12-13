package ru.consulting.loading;

import ru.consulting.data.Table;

import java.util.ArrayList;

public interface TableLoader {

    ArrayList<Table> loadTables(String path);
}
