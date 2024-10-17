package utils;

import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;
import objects.Department;
import objects.Reviewer;
import objects.Student;
import objects.AllData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderOds{
    private static ArrayList<Student> readStudents(Sheet st, Sheet studentPrior){
        if (st == null || studentPrior == null)
            throw new IllegalArgumentException("Sheet can`t be null");

        ArrayList<Student> students = new ArrayList<>();

        int numRowsStudents = st.getMaxRows();

        for(int row = 1; row < numRowsStudents; row++){
            int id = (int)Double.parseDouble(st.getDataRange().getCell(row, 0).getValue().toString());
            String surname = st.getDataRange().getCell(row, 1).getValue().toString();
            String name = st.getDataRange().getCell(row, 2).getValue().toString();

            students.add(new Student(id, name, surname));
        }

        int numRowsPriorities = studentPrior.getMaxRows();
        for(int row = 1; row < numRowsPriorities; row++){
            String surname = studentPrior.getDataRange().getCell(row, 0).getValue().toString();
            String name = studentPrior.getDataRange().getCell(row, 1).getValue().toString();
            int pr1 = (int)Double.parseDouble(studentPrior.getDataRange().getCell(row, 2).getValue().toString());
            int pr2 = (int)Double.parseDouble(studentPrior.getDataRange().getCell(row, 3).getValue().toString());
            int pr3 = (int)Double.parseDouble(studentPrior.getDataRange().getCell(row, 4).getValue().toString());

            for(Student student: students){
                //if (student.getName().equals(name) && student.getSurname().equals(surname)){
                if(student.getId() == row){
                    student.setPr1(pr1);
                    student.setPr2(pr2);
                    student.setPr3(pr3);
                }
            }
        }

        return students;
    }

    private static ArrayList<Reviewer> readReviewers(Sheet rev, ArrayList<Department> departments, Sheet revPrior){
        if (rev == null || revPrior == null)
            throw new IllegalArgumentException("Sheet can`t be null");

        ArrayList<Reviewer> reviewers = new ArrayList<>();

        int numRows = rev.getMaxRows();

        for(int row = 1; row < numRows; row++) {
            int id = (int)Double.parseDouble(rev.getDataRange().getCell(row, 0).getValue().toString());
            String surname = rev.getDataRange().getCell(row, 1).getValue().toString();
            String name = rev.getDataRange().getCell(row, 2).getValue().toString();

            String departmentName = rev.getDataRange().getCell(row, 3).getValue().toString();
            Department department = null;

            for (Department dpt : departments) {
                if (dpt.getName().equals(departmentName)) {
                    department = dpt;
                    break;
                }
            }
            if (department == null){
                department = new Department(departmentName);
                departments.add(department);
            }

            reviewers.add(new Reviewer(id, name, surname, department.getId()));
        }

        int numRowsPriorities = revPrior.getMaxRows();
        for(int row = 1; row < numRowsPriorities; row++){
            int id = (int)Double.parseDouble(revPrior.getDataRange().getCell(row, 0).getValue().toString());
            int pr1 = (int)Double.parseDouble(revPrior.getDataRange().getCell(row, 4).getValue().toString());
            int pr2 = (int)Double.parseDouble(revPrior.getDataRange().getCell(row, 5).getValue().toString());
            int pr3 = (int)Double.parseDouble(revPrior.getDataRange().getCell(row, 6).getValue().toString());
            int pr4 = (int)Double.parseDouble(revPrior.getDataRange().getCell(row, 7).getValue().toString());
            int pr5 = (int)Double.parseDouble(revPrior.getDataRange().getCell(row, 8).getValue().toString());

            for(Reviewer reviewer: reviewers){
                if (reviewer.getId() == id){
                    reviewer.setPr1(pr1);
                    reviewer.setPr2(pr2);
                    reviewer.setPr3(pr3);
                    reviewer.setPr4(pr4);
                    reviewer.setPr5(pr5);
                    break;
                }
            }
        }

        return reviewers;
    }

    public static AllData read(String path){
        try {
            SpreadSheet spread = new SpreadSheet(new File(path));

            Sheet studentTable = spread.getSheet("Студенты");
            Sheet reviewerTable = spread.getSheet("Преподаватели");
            Sheet studentPrioritiesTable = spread.getSheet("Приоритет студентов");
            Sheet reviewerPrioritiesTable = spread.getSheet("Приоритет преподавателей");

            ArrayList<Student> students = readStudents(studentTable, studentPrioritiesTable);

            ArrayList<Department> departments = new ArrayList<>();
            ArrayList<Reviewer> reviewers = readReviewers(reviewerTable, departments, reviewerPrioritiesTable);

            return new AllData(students, reviewers, departments);

        } catch (IOException | IllegalArgumentException e){
            e.printStackTrace();
        }
        return null;
    }
}
