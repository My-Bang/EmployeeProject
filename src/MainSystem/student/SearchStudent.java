package MainSystem.student;

/**
 * 학번을 이용하여 학생을 검색하는 기능을 정의하는 인터페이스.
 * <p>
 * 이 인터페이스는 {@link StudentOutput}을 확장하며,
 * 학번을 기준으로 특정 학생을 검색하는 기능을 제공한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public interface SearchStudent extends StudentOutput {

    /**
     * 특정 학번을 가진 학생을 검색한다.
     *
     * @param sno 검색할 학생의 학번
     * @return 학번에 해당하는 학생 객체 (없으면 null 반환)
     */
    Student search(String sno);
}
