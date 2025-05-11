package student;

import student.DTO.StudentDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class StudentManager extends StudentFileIO{
    private static final String FILE_PATH = "students.json";
    private List<StudentDTO> students;

    public StudentManager() {
        students = new ArrayList<>();
        loadStudents();
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("학번: ");
        String sno = scanner.next();
        System.out.print("이름: ");
        String name = scanner.next();
        System.out.print("국어 점수: ");
        int korean = scanner.nextInt();
        System.out.print("영어 점수: ");
        int english = scanner.nextInt();
        System.out.print("수학 점수: ");
        int math = scanner.nextInt();
        System.out.print("과학 점수: ");
        int science = scanner.nextInt();

        StudentDTO student = new StudentDTO(sno, name, korean, english, math, science);
        students.add(student);
        saveStudents();
    }

    @Override
    public void print() {
        if (students.isEmpty()) {
            System.out.println("학생 정보가 없습니다.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. 전체 학생 출력");
        System.out.println("2. 학번으로 검색");
        System.out.print("선택: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            frame();
            for (StudentDTO student : students) {
                System.out.println(student);
            }
        } else if (choice == 2) {
            System.out.print("검색할 학번: ");
            String sno = scanner.next();
            frame();
            students.stream()
                    .filter(student -> student.getSno().equals(sno))
                    .forEach(System.out::println);
        }
    }

    @Override
    public void delete() {
        if (students.isEmpty()) {
            System.out.println("학생 정보가 없습니다.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제할 학번: ");
        String sno = scanner.next();
        students.removeIf(student -> student.getSno().equals(sno));
        saveStudents();
    }

    @Override
    public void sort() {
        if (students.isEmpty()) {
            System.out.println("학생 정보가 없습니다.");
            return;
        }

        // 총점 기준으로 정렬
        students.sort(Comparator.comparingInt(StudentDTO::getTotal).reversed());

        // 정렬된 학생 목록 출력
        System.out.println("총점 기준으로 정렬된 학생 목록:");
        students.forEach(System.out::println);

        // 정렬된 데이터를 JSON 파일에 저장
        saveStudents();
    }
    public void saveStudents() {
        List<String> jsonList = new ArrayList<>();
        for (StudentDTO student : students) {
            Map<String, Object> studentMap = new HashMap<>();
            studentMap.put("sno", student.getSno());
            studentMap.put("name", student.getName());
            studentMap.put("korean", student.getKorean());
            studentMap.put("english", student.getEnglish());
            studentMap.put("math", student.getMath());
            studentMap.put("science", student.getScience());
            jsonList.add(mapToJson(studentMap));
        }

        String jsonArray = "[" + String.join(",", jsonList) + "]";
        try {
            Files.write(Paths.get(FILE_PATH), jsonArray.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadStudents() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) return;

        try {
            String jsonText = new String(Files.readAllBytes(path));
            if (!jsonText.isEmpty()) {
                List<Map<String, Object>> studentMaps = jsonToList(jsonText);
                for (Map<String, Object> studentMap : studentMaps) {
                    StudentDTO student = new StudentDTO(
                            (String) studentMap.get("sno"),
                            (String) studentMap.get("name"),
                            (int) studentMap.get("korean"),
                            (int) studentMap.get("english"),
                            (int) studentMap.get("math"),
                            (int) studentMap.get("science")
                    );
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
