package MainSystem.student;

/**
 * 학생 데이터를 입력하는 기능을 정의하는 인터페이스.
 * <p>
 * 이 인터페이스를 구현하는 클래스는 학생 데이터를 저장하는 기능을 제공해야 한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public interface StudentInput {

    /**
     * 학생 데이터를 입력하는 메서드.
     * <p>
     * 이 메서드는 학생 데이터를 받아서 저장하는 기능을 수행해야 한다.
     * 구현 클래스에서 실제 입력 방식(DB 저장, 파일 저장 등)을 정의해야 한다.
     * </p>
     *
     * @param student 저장할 학생 객체
     */
    void input(Student student);
}

