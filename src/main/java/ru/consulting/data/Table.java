package ru.consulting.data;

public class Table implements Comparable<Table> {

    private int id;
    private String value;

    public Table(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Table o) {
        return this.getId() - o.getId();
    }
}
