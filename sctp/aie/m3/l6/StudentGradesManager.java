package sctp.aie.m3.l6;

import java.util.ArrayList;
import java.util.List;

public class StudentGradesManager {
    private List<Student> students;

    public StudentGradesManager(List<Student> students) {
        this.students = students;
    }

    public StudentGradesManager() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudent(int index) {
        if (index >= 0 && index < students.size())
            return students.get(index);
        else
            return null;
    }

    public int getStudentCount() {
        return students.size();
    }

    public Student getStudentWithHighestGrade() {
        return students.stream()
                // .reduce((s1, s2) -> s1.grade() > s2.grade() ? s1 : s2)
                // Comparator-based approach for better readability
                // Compare student grades in pairwise fashion and return the student with the
                // highest grade
                .max((s1, s2) -> Double.compare(s1.grade(), s2.grade()))
                .orElse(null);
    }

    public double getAverageGrade() {
        return students.stream()
                .mapToDouble(Student::grade)
                .average()
                .orElse(0.0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StudentGradesManager manager = new StudentGradesManager();
        manager.addStudent(new Student("Alice", 1, 85.5));
        manager.addStudent(new Student("Bob", 2, 92.0));
        manager.addStudent(new Student("Charlie", 3, 78.0));
        manager.addStudent(new Student("Diana", 4, 88.5));
        manager.addStudent(new Student("Edward", 5, 95.0));

        System.out.println(manager.toString());
        System.out.println("Total Students: " + manager.getStudentCount());

        System.out.println("\nStudent with the highest grade: " + manager.getStudentWithHighestGrade());
        System.out.println("Average grade of all students: " + manager.getAverageGrade());
    }
}
