package MainSystem.student;

/**
 * 학생 정보를 출력하는 기능을 정의하는 인터페이스.
 * <p>
 * 이 인터페이스를 구현하는 클래스는 다양한 형태로 학생 정보를 출력할 수 있다.
 * 출력할 데이터는 {@code StudentManager}에서 가공하여 제공한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public interface StudentOutput {

    /**
     * 학생 데이터를 출력하는 메서드.
     * <p>
     * 이 메서드는 가공된 데이터를 출력하는 역할만 수행하며,
     * 출력할 데이터는 {@code StudentManager}에서 준비된다.
     * </p>
     */
    void output();
}
