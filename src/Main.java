import java.util.ArrayList;
import java.util.Scanner;

class Student {
  private int StudentID;
  private String StudentName;
  private int TotalClasses;
  private int AttendedClasses;

  public Student(String name, int ID) {
    this.StudentName = name;
    this.StudentID = ID;
    this.TotalClasses = 0;
    this.AttendedClasses = 0;
  }

  public void TakeAttendance(boolean isHere) {
    TotalClasses++;
    if (isHere) {
      AttendedClasses++;
    }
  }

  public double AttendancePercentage() {
    if (TotalClasses == 0) {
      return 0;
    }
    return (AttendedClasses * 100.0) / TotalClasses;
  }

  public void Display() {
    System.out.println("Name: " + StudentName);
    System.out.println("ID: " + StudentID);
    System.out.println("Attended: " + AttendedClasses + "/" + TotalClasses);
    System.out.printf("Percentage: %.2f%%\n", AttendancePercentage());
    System.out.println("----------------------");
  }

  public int getStudentId() {
    return StudentID;
  }
}

class Course {
  private String course;
  private ArrayList<Student> students;

  public Course(String course) {
    this.course = course;
    students = new ArrayList<>();
  }

  public void AddStudent(Student student) {
    students.add(student);
    System.out.println("Student added successfully.");
  }

  public Student findStudent(int ID) {
    for (Student s : students) {
      if (s.getStudentId() == ID) {
        return s;
      }
    }
    return null;
  }

  public void markAttendance(int id, boolean isPresent) {
    Student s = findStudent(id);
    if (s != null) {
      s.TakeAttendance(isPresent);
      System.out.println("Attendance marked.");
    } else {
      System.out.println("Student not found.");
    }
  }

  public void displayAllStudents() {
    if (students.isEmpty()) {
      System.out.println("No students available.");
    } else {
      for (Student s : students) {
        s.Display();
      }
    }
  }
}

public class Main {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    Course course = new Course("Software Course");

    int choice;

    do {
      System.out.println("\n===== Attendance System =====");
      System.out.println("1. Add Student");
      System.out.println("2. Mark Attendance");
      System.out.println("3. Show All Students");
      System.out.println("4. Exit");
      System.out.print("Enter choice: ");

      choice = input.nextInt();

      switch (choice) {

        case 1:
          System.out.print("Enter student ID: ");
          int id = input.nextInt();
          input.nextLine();

          System.out.print("Enter student name: ");
          String name = input.nextLine();

          Student student = new Student(name, id);
          course.AddStudent(student);
          break;

        case 2:
          System.out.print("Enter student ID: ");
          int markId = input.nextInt();

          System.out.print("Is student present? (true/false): ");
          boolean present = input.nextBoolean();

          course.markAttendance(markId, present);
          break;

        case 3:
          course.displayAllStudents();
          break;

        case 4:
          System.out.println("Exiting system...");
          break;

        default:
          System.out.println("Invalid choice.");
      }

    } while (choice != 4);

    input.close();
  }
}