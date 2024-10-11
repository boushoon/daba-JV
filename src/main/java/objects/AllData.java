package objects;

import java.util.ArrayList;

public class AllData {
    ArrayList<Student> students;
    ArrayList<Reviewer> reviewers;
    ArrayList<Department> departments;
    ArrayList<StudentPriorities> studentPriorities;
    ArrayList<ReviewerPriorities> reviewerPriorities;

    public AllData(ArrayList<Student> students, ArrayList<Reviewer> reviewers, ArrayList<Department> departments,
                   ArrayList<StudentPriorities> studentPriorities, ArrayList<ReviewerPriorities> reviewerPriorities) {
        this.students = students;
        this.reviewers = reviewers;
        this.departments = departments;
        this.studentPriorities = studentPriorities;
        this.reviewerPriorities = reviewerPriorities;
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

    public ArrayList<StudentPriorities> getStudentPriorities() {
        return studentPriorities;
    }

    public ArrayList<ReviewerPriorities> getReviewerPriorities() {
        return reviewerPriorities;
    }
}
