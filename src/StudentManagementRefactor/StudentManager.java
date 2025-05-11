package StudentManagementRefactor;

import java.util.HashMap;

public class StudentManager {
    private static final StudentManager INSTANCE = new StudentManager();
    private HashMap<String, Student> studentList = new HashMap<>();
    private final StudentDBIO studentDBIO;// StudentDBIO를 직접 참조하지 않도록 변경

    private StudentManager() {
        this.studentDBIO = StudentDBIO.getInstance(); // ✅ 생성자에서 한 번만 초기화
    }

    public static StudentManager getInstance() {
        return INSTANCE;
    }

    public void setStudentList(HashMap<String, Student> studentList) {
        this.studentList.clear();
        this.studentList.putAll(studentList);
    }

    public void loadData() {
        studentList = studentDBIO.loadData();
    }

    public Student search(String sno) {
        return studentDBIO.search(sno); // 검색은 DB에서 처리하도록 변경
    }

    public void sortByTotal() {
        studentList = studentDBIO.sortByTotal();
    }

    public void sortByName() {
        studentList = studentDBIO.sortByName();
    }

    public void input(Student student) {
        studentDBIO.input(student); // 데이터를 DB에 저장하도록 변경
        studentList.put(student.getSno(), student); // 내부 리스트도 업데이트
    }

    public void deleteStudent(String sno) {
        studentList = studentDBIO.deleteStudent(sno); // DB에서 삭제 후 최신 리스트 반영
    }

    public void output() {
        if (studentList.isEmpty()) {
            System.out.println("학생 데이터가 없습니다.");
        } else {
            System.out.println(Student.getTableHeader());
            for (Student student : studentList.values()) {
                System.out.println(student);
            }
        }
    }
}
