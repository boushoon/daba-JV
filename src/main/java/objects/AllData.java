package objects;

import java.util.ArrayList;

public class AllData {
    ArrayList<Student> students;
    ArrayList<Reviewer> reviewers;
    ArrayList<Department> departments;

    public AllData(ArrayList<Student> students, ArrayList<Reviewer> reviewers, ArrayList<Department> departments) {
        this.students = students;
        this.reviewers = reviewers;
        this.departments = departments;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Reviewer> getReviewers() {
        return reviewers;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }
}
