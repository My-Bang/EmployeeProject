package StudentManagementRefactor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.*;

public class StudentDBIO extends ObjectIO implements StudentIO {
    // * 데이터 저장, 로드, JSON 파싱 담당 (saveData(), loadData())

    private static final StudentDBIO instance = new StudentDBIO();
    private static final String filePath = "src/StudentManagementRefactor/students.json";

    protected StudentDBIO() {}

    public static StudentDBIO getInstance() {
        return instance;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public HashMap<String, Student> loadData() {
        return parseJson();
    }

    @Override
    public void input(Student student) {
        HashMap<String, Student> studentMap = parseJson();
        studentMap.put(((Student) student).getSno(), (Student) student);
        saveJson(studentMap);

        System.out.println("학생 정보가 입력되었습니다.");
    }

    @Override
    public Student search(String sno) {
        HashMap<String, Student> studentMap = parseJson();
        return studentMap.getOrDefault(sno, null);
    }

    @Override
    public HashMap<String, Student> sortByName() {
        HashMap<String, Student> studentMap = parseJson();
        return sortByComparator(studentMap, Comparator.comparing(Student::getName, Collator.getInstance()));
    }

    @Override
    public HashMap<String, Student> sortByTotal() {
        HashMap<String, Student> studentMap = parseJson();
        return sortByComparator(studentMap, Comparator.comparingInt(Student::getTotal).reversed());
    }

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

        // 삭제된 후의 테이블 반환
        return studentMap;
    }


    @Override
    public void output() {

    }

    /**
     * JSON 파일을 읽어 HashMap<String, Student>으로 변환하는 메서드
     */
    private HashMap<String, Student> parseJson() {
        HashMap<String, Student> students = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return students; // 파일이 없으면 빈 HashMap 반환
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
     * HashMap<String, Student>을 JSON 파일로 저장하는 메서드
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
     * 주어진 Comparator를 이용해 정렬된 HashMap을 반환하는 메서드
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
