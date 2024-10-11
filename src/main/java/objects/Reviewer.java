package objects;

public class Reviewer {
    private int id;
    private String name;
    private String surname;
    private int departmentID;

    public Reviewer(int id, String name, String surname, int departmentID) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.departmentID = departmentID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getDepartmentID() {
        return departmentID;
    }
}
