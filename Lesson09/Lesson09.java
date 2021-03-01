import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lesson09 {

    static class Student {
        protected String name;
        protected List<Course> allCourses;

        public Student(String name, List<Course> allCourses) {
            this.name = name;
            this.allCourses = allCourses;
        }

        public String getName() {
            return name;
        }

        public List<Course> getAllCourses() {
            return allCourses;
        }
    }

    static class Course {
        protected String name;

        public Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static List<Course> getUniqueCourses(List<Student> students) {
        return students.stream()
                .map(Student::getAllCourses)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    static List<Student> getMostCourses(List<Student> students) {
        return students.stream()
                .sorted((s1, s2) -> s2.getAllCourses().size() - s1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());
    }

   static List<Student> getStudentList(List<Student> students, Course course) {
        return students.stream()
                .filter(student -> student.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        Course math = new Course("Math");
        Course english = new Course("English");
        Course literature = new Course("Literature");
        Course history = new Course("History");
        Course science = new Course("Science");
        Course pe = new Course("PE");

        Student student1 = new Student("Jack", Arrays.asList(math, english, literature, history, science));
        Student student2 = new Student("Jill", Arrays.asList(pe, science, math, english));
        Student student3 = new Student("Alexander Hamilton", Arrays.asList(history));
        Student student4 = new Student("Piglet Pyotr", Arrays.asList(pe, english));
        Student student5 = new Student("Ella the Cannibal", Arrays.asList(english, literature, science));
        Student student6 = new Student("Valentina Tereshkova", Arrays.asList(pe, math, science, history));

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5, student6);


        System.out.println("1. Unique courses list:");
        getUniqueCourses(students).stream()
                .map(Course::getName)
                .forEach(System.out::println);

        System.out.println("\n2. Three students partaking in the most courses:");
        getMostCourses(students).stream()
                .map(Student::getName)
                .forEach(System.out::println);

        System.out.println("\n3. Students partaking in the PE course:");
        getStudentList(students, pe).stream()
                .map(Student::getName)
                .forEach(System.out::println);


    }


}
