package MainSystem.student;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 학생 데이터 관리 클래스.
 * <p>
 * 학생 데이터를 관리하며, 데이터 저장 및 조회, 정렬 등의 기능을 제공한다.
 * 싱글턴 패턴을 사용하여 하나의 인스턴스만 유지한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public class StudentManager {
    /** StudentManager의 유일한 인스턴스 (싱글턴). */
    private static final StudentManager INSTANCE = new StudentManager();

    /** 학생 데이터를 저장하는 HashMap. */
    private Map<String, Student> studentList = new LinkedHashMap<>();

    /** 학생 데이터를 저장하고 불러오는 StudentDBIO 객체. */
    private final StudentDBIO studentDBIO;

    /**
     * 생성자 - 싱글턴 패턴을 적용하여 외부에서 인스턴스 생성 방지.
     */
    private StudentManager() {
        this.studentDBIO = StudentDBIO.getInstance(); // 생성자에서 한 번만 초기화
    }

    /**
     * StudentManager의 유일한 인스턴스를 반환한다.
     *
     * @return StudentManager의 싱글턴 인스턴스
     */
    public static StudentManager getInstance() {
        return INSTANCE;
    }

    /**
     * 데이터베이스에서 학생 데이터를 로드한다.
     *
     */
    public void loadData() {
        studentList = studentDBIO.loadData();
    }

    /**
     * 학번을 이용하여 학생을 검색한다.
     *
     * @param sno 검색할 학생의 학번
     */
    public void search(String sno) {
        printStudent(studentDBIO.search(sno));
    }

    /**
     * 총점 기준으로 학생 목록을 정렬한다.
     *
     */
    public void sortByTotal() {
        studentList = studentDBIO.sortByTotal();
    }

    /**
     * 이름 기준으로 학생 목록을 정렬한다.
     *
     */
    public void sortByName() {
        studentList = studentDBIO.sortByName();
    }

    /**
     * 새로운 학생 정보를 추가한다.
     * 데이터는 데이터베이스에도 저장된다.
     *
     * @param student 추가할 학생 객체
     */
    public void input(Student student) {
        studentDBIO.input(student);
        studentList = studentDBIO.loadData();
        //studentList.put(student.getSno(), student); //사실 이부분은 리팩토링 해야됨 json을 로드해야됨 데이터 이원화 가능성이 여전히 있음.
    }

    /**
     * 학번을 이용하여 학생을 삭제한다.
     * 삭제 후 최신 학생 목록을 반영한다.
     *
     * @param sno 삭제할 학생의 학번
     */
    public void deleteStudent(String sno) {
        studentList = studentDBIO.deleteStudent(sno);
    }

    /**
     * 저장된 학생 데이터를 출력한다.
     */
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

    /**
     * 특정 학생 객체를 출력하는 메서드.
     *
     * @param student 출력할 학생 객체
     */
    public void printStudent(Student student) {
        if (student == null) {
            System.out.println("학생 정보를 찾을 수 없습니다.");
            return;
        }
        System.out.println(Student.getTableHeader());
        System.out.println(student);
    }

}
