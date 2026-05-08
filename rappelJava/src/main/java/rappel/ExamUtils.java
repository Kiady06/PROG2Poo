package rappel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExamUtils {

    public static class Student {
        public String firstName;
        public String lastName;
        public Map<String, Double> grades; 

        public Student(String firstName, String lastName, Map<String, Double> grades) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.grades = grades;
        }
    }

    public static List<String> getRetakeExams(Student student) {
        List<String> result = new ArrayList<>();

        if (student.grades == null || student.grades.isEmpty()) return result;

        for (Map.Entry<String, Double> entry : student.grades.entrySet()) {
            Double grade = entry.getValue();
            if (grade == null || !Double.isFinite(grade)) continue;
            if (grade < 10) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}
