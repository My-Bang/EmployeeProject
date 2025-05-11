package MainSystemV1.student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.*;

/**
 * 학생 정보를 JSON 파일로 저장하고 불러오는 클래스.
 * <p>
 * 학생 데이터를 파일에서 로드하고 저장하며, 검색 및 정렬 기능을 제공한다.
 * 싱글턴 패턴을 적용하여 하나의 인스턴스만 유지한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public class StudentDBIO extends ObjectIO implements StudentIO {
    /** StudentDBIO의 유일한 인스턴스 (싱글턴). */
    private static final StudentDBIO instance = new StudentDBIO();

    /** 학생 데이터를 저장할 JSON 파일 경로. */
    private static final String filePath = "src/MainSystem.StudentManagementRefactor/students.json";

    /**
     * 생성자 - 싱글턴 패턴을 적용하여 외부에서 인스턴스 생성 방지.
     */
    protected StudentDBIO() {}

    /**
     * StudentDBIO의 유일한 인스턴스를 반환한다.
     *
     * @return StudentDBIO의 싱글턴 인스턴스
     */
    public static StudentDBIO getInstance() {
        return instance;
    }

    /**
     * 학생 정보가 저장된 JSON 파일 경로를 반환한다.
     *
     * @return JSON 파일 경로 (String)
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * JSON 파일에서 학생 데이터를 로드하여 HashMap으로 반환한다.
     *
     * @return 학생 데이터가 저장된 HashMap
     */
    @Override
    public Map<String, Student> loadData() {
        return parseJson();
    }

    @Override
    public void saveData(Map data) throws IOException {
        //
    }

    /**
     * 새로운 학생 데이터를 JSON 파일에 추가한다.
     *
     * @param student 추가할 학생 객체
     */
    @Override
    public void input(Student student) {
        HashMap<String, Student> studentMap = parseJson();
        studentMap.put(student.getSno(), student);
        saveJson(studentMap);

        System.out.println("학생 정보가 입력되었습니다.");
    }

    /**
     * 특정 학번을 가진 학생을 검색한다.
     *
     * @param sno 검색할 학생의 학번
     * @return 학번에 해당하는 학생 객체 (없으면 null 반환)
     */
    @Override
    public Student search(String sno) {
        HashMap<String, Student> studentMap = parseJson();
        return studentMap.getOrDefault(sno, null);
    }

    /**
     * json 데이터의 학생 목록을 이름 기준으로 정렬하고 반환한다.
     *
     * @return 이름 기준으로 정렬된 HashMap
     */
    @Override
    public HashMap<String, Student> sortByName() {
        HashMap<String, Student> studentMap = parseJson();
        return sortByComparator(studentMap, Comparator.comparing(Student::getName, Collator.getInstance()));
    }

    /**
     * json 데이터의 학생 점수를 기준으로 정렬하고 반환한다.
     *
     * @return 총점 기준으로 정렬된 HashMap
     */
    @Override
    public HashMap<String, Student> sortByTotal() {
        HashMap<String, Student> studentMap = parseJson();
        return sortByComparator(studentMap, Comparator.comparingInt(Student::getTotal).reversed());
    }

    /**
     * 특정 학번의 학생을 삭제하고, 변경된 학생 목록을 반환한다.
     *
     * @param sno 삭제할 학생의 학번
     * @return 삭제 후의 학생 목록 HashMap
     */
    @Override
    public HashMap<String, Student> deleteStudent(String sno) {
        HashMap<String, Student> studentMap = parseJson();

        if (!studentMap.containsKey(sno)) {
            System.out.println("학번 '" + sno + "'에 해당하는 학생을 찾을 수 없습니다.");
            return studentMap; // 삭제하지 않고 기존 테이블 반환
        }

        // 학생 삭제
        studentMap.remove(sno);

        // 변경된 데이터 저장
        saveJson(studentMap);

        System.out.println("학번 '" + sno + "' 학생 정보가 삭제되었습니다.");

        return studentMap;
    }

    /**
     * 저장된 학생 정보를 출력한다. (미구현)
     */
    @Override
    public void output() {
        // 출력 기능 미구현
    }

    /**
     * JSON 파일을 읽어 HashMap<String, Student>으로 변환하는 메서드.
     *
     * @return 학생 데이터가 저장된 HashMap
     */
    private HashMap<String, Student> parseJson() {
        HashMap<String, Student> students = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return students; // 공 데이터를 담아서 nul 방지
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            if (json.trim().isEmpty() || json.equals("{ \"students\": [] }")) {
                return students;
            }

            JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
            JSONArray jsonArray = (JSONArray) jsonObject.get("students");

            for (Object obj : jsonArray) {
                JSONObject studentObj = (JSONObject) obj;
                Student student = new Student(
                        (String) studentObj.get("sno"),
                        (String) studentObj.get("name"),
                        ((Long) studentObj.get("korean")).intValue(),
                        ((Long) studentObj.get("english")).intValue(),
                        ((Long) studentObj.get("math")).intValue(),
                        ((Long) studentObj.get("science")).intValue(),
                        ((Long) studentObj.get("total")).intValue(),
                        ((Double) studentObj.get("average")).floatValue(),
                        (String) studentObj.get("grade")
                );
                students.put(student.getSno(), student);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return students;
    }

    /**
     * HashMap<String, Student>을 JSON 파일로 저장하는 메서드.
     *
     * @param studentMap 저장할 학생 목록 HashMap
     */
    private void saveJson(HashMap<String, Student> studentMap) {
        JSONArray studentArray = new JSONArray();

        for (Student student : studentMap.values()) {
            JSONObject studentObj = new JSONObject();
            studentObj.put("sno", student.getSno());
            studentObj.put("name", student.getName());
            studentObj.put("korean", student.getKorean());
            studentObj.put("english", student.getEnglish());
            studentObj.put("math", student.getMath());
            studentObj.put("science", student.getScience());
            studentObj.put("total", student.getTotal());
            studentObj.put("average", student.getAverage());
            studentObj.put("grade", student.getGrade());

            studentArray.add(studentObj);
        }

        JSONObject newJsonObject = new JSONObject();
        newJsonObject.put("students", studentArray);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(newJsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 주어진 Comparator를 이용해 정렬된 HashMap을 반환하는 메서드.
     *
     * @param studentMap 정렬할 학생 목록 HashMap
     * @param comparator 정렬 기준
     * @return 정렬된 HashMap
     */
    private HashMap<String, Student> sortByComparator(HashMap<String, Student> studentMap, Comparator<Student> comparator) {
        List<Student> sortedList = new ArrayList<>(studentMap.values());
        sortedList.sort(comparator);

        HashMap<String, Student> sortedMap = new LinkedHashMap<>(); //순서를 보장하기 위해
        for (Student student : sortedList) {
            sortedMap.put(student.getSno(), student);
        }

        return sortedMap;
    }
}



