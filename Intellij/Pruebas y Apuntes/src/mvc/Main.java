package mvc;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Hugo", "Palma");

        StudentController studentController = new StudentController(student1, new StudentViewJava());

        studentController.updateView();
    }
}

class StudentController {
    private Student student;
    private StudentView studentView;

    public StudentController(Student student, StudentView studentView) {
        this.student = student;
        this.studentView = studentView;
    }

    void updateView() {
        studentView.printStudentDetails(this.student);
    }
}

interface StudentView {
    void printStudentDetails(Student student);
}
class StudentViewJava implements StudentView {
    public void printStudentDetails(Student student) {
        System.out.printf("Student name: %s\nCity: %s\n", student.getName(), student.getCity());
    }
}

class StudentViewHtml implements StudentView {
    @Override
    public void printStudentDetails(Student student) {
        System.out.println("<html><head></head><body>");
        System.out.printf("<b>Name: %s</b>\n<b>City: %s</b>\n", student.getName(), student.getCity());
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

    public Student(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
