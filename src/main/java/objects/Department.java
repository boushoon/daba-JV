package objects;

public class Department {
    private static int count = 1;
    private int id;
    private String name;

    public Department(String name) {
        this.id = count++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
