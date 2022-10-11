package designpatterns.MVC;

/*

MODEL -> Dades "de negoci"


CONTROLADOR -> Intermediari


VISTA -> Objecte que s'encarrega de presentar les dades


 */

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("Mary");
        s1.setCity("Palma");
        StudentView sv = new StudentViewHTML();
        StudentController studentController = new StudentController(s1, sv);
        studentController.updateView();
    }
}

class StudentController {
    private Student student;
    private StudentView studentView;

    StudentController(Student student, StudentView sv) {
        this.student = student;
        this.studentView = sv;
    }

    void updateView() {
        studentView.printStudentDetails(this.student);
    }
}

interface StudentView {
    void printStudentDetails(Student s);
}

class StudentView1 implements StudentView {
    public void printStudentDetails(Student s) {
        System.out.printf("Student: name->%s, city->%s\n", s.getName(), s.getCity());
    }
}

class StudentViewHTML implements StudentView {
    @Override
    public void printStudentDetails(Student s) {
        System.out.println("<html><head></head><body>");
        System.out.printf("<b>Name</b>: %s\n", s.getName());
        System.out.printf("<b>City</b>: %s\n", s.getCity());
        System.out.println("</body></html>");
    }
}

class Student {
    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}