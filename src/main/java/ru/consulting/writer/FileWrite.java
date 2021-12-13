package ru.consulting.writer;

import ru.consulting.data.JoiningTable;

import java.util.List;

public interface FileWrite {

    void writer(List<JoiningTable> joiningTables,String path);
}
