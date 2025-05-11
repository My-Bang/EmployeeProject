package StudentManagementRefactor;


import lombok.Data;

@Data
public class Student {
    //DTO 클래스로 서 역할을 한다
    private String sno;
    private String name;
    private int korean;
    private int english;
    private int math;
    private int science;
    private int total;
    private double average;
    private String grade;

    public Student(String sno, String name, int korean, int english, int math, int science, int total, double average, String grade) {
        this.sno = sno;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
        this.science = science;
        this.total = total;
        this.average = average;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("| %-6s | %-6s | %5d | %5d | %5d | %5d | %6d | %7.2f | %-2s |",
                sno, name, korean, english, math, science, total, average, grade);
    }

    // ✅ 테이블 헤더 추가 (정렬 유지)
    public static String getTableHeader() {
        return String.format("| %-5s | %-7s | %4s | %4s | %4s | %4s | %5s | %6s | %1s |",
                "학번", "이름", "국어", "영어", "수학", "과학", "총점", "평균", "등급") +
                "\n" + "-".repeat(77); // 구분선 길이 조정
    }


}
