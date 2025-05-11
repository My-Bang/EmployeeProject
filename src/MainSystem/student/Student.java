package MainSystem.student;

import lombok.Data;

/**
 * 학생 정보를 저장하는 포맷을 제공하는 DTO (Data Transfer Object) 클래스.
 * <p>
 * 학생의 학번, 이름, 과목별 점수, 총점, 평균, 학점을 저장하며,
 * 데이터를 전송하는 역할을 한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
@Data
public class Student {

    /** 학번 */
    private String sno;

    /** 학생 이름 */
    private String name;

    /** 국어 점수 */
    private int korean;

    /** 영어 점수 */
    private int english;

    /** 수학 점수 */
    private int math;

    /** 과학 점수 */
    private int science;

    /** 총점 */
    private int total;

    /** 평균 점수 */
    private double average;

    /** 학점 */
    private String grade;

    /**
     * 학생 정보를 생성하는 생성자.
     *
     * @param sno 학번
     * @param name 학생 이름
     * @param korean 국어 점수
     * @param english 영어 점수
     * @param math 수학 점수
     * @param science 과학 점수
     * @param total 총점
     * @param average 평균 점수
     * @param grade 학점
     */
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

    /**
     * 학생 정보를 테이블 형식으로 반환한다.
     *
     * @return 테이블 형식의 학생 정보 문자열
     */
    @Override
    public String toString() {
        return String.format("| %-6s | %-6s | %5d | %5d | %5d | %5d | %6d | %7.2f | %-2s |",
                sno, name, korean, english, math, science, total, average, grade);
    }

    /**
     * 학생 정보를 출력할 때 사용할 테이블 헤더를 반환한다.
     *
     * @return 학생 정보 테이블 헤더 문자열
     */
    public static String getTableHeader() {
        return String.format("| %-5s | %-7s | %4s | %4s | %4s | %4s | %5s | %6s | %1s |",
                "학번", "이름", "국어", "영어", "수학", "과학", "총점", "평균", "등급") +
                "\n" + "-".repeat(77); // 구분선 길이 조정
    }
}
