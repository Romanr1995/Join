package ru.consulting.data;

public class JoiningTable {

    private int id;
    private String valueTableA;
    private String valueTableB;

    public JoiningTable(int id, String valueTableA, String getValueTableB) {
        this.id = id;
        this.valueTableA = valueTableA;
        this.valueTableB = getValueTableB;
    }

    public int getId() {
        return id;
    }

    public String getValueTableA() {
        return valueTableA;
    }

    public String getValueTableB() {
        return valueTableB;
    }

    @Override
    public String toString() {
        return "JoiningTable{" +
                "id=" + id +
                ", valueTableA='" + valueTableA + '\'' +
                ", valueTableB='" + valueTableB + '\'' +
                '}';
    }
}
