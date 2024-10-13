package objects;

public class Reviewer {
    private final int id;
    private final String name;
    private final String surname;
    private final int departmentID;
    private int pr1, pr2, pr3, pr4, pr5;

    public Reviewer(int id, String name, String surname, int departmentID) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.departmentID = departmentID;
    }

    public void setPr1(int pr1) {
        this.pr1 = pr1;
    }

    public void setPr2(int pr2) {
        this.pr2 = pr2;
    }

    public void setPr3(int pr3) {
        this.pr3 = pr3;
    }

    public void setPr4(int pr4) {
        this.pr4 = pr4;
    }

    public void setPr5(int pr5) {
        this.pr5 = pr5;
    }

    public int getPr1() {
        return pr1;
    }

    public int getPr2() {
        return pr2;
    }

    public int getPr3() {
        return pr3;
    }

    public int getPr4() {
        return pr4;
    }

    public int getPr5() {
        return pr5;
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
