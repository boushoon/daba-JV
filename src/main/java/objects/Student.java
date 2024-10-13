package objects;

public class Student {
    private final int id;
    private final String name;
    private final String surname;
    private int pr1, pr2, pr3;

    public Student(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public int getPr1() {
        return pr1;
    }

    public int getPr2() {
        return pr2;
    }

    public int getPr3() {
        return pr3;
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
}
