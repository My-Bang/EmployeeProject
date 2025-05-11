package MainSystem.student;

import java.util.HashMap;

/**
 * 학생 데이터를 정렬하는 기능을 정의하는 인터페이스.
 * <p>
 * 이 인터페이스를 구현하는 클래스는 학생 목록을 정렬할 수 있어야 한다.
 * 정렬 방식은 다음과 같다:
 * <ul>
 *     <li>이름 순 정렬 (오름차순)</li>
 *     <li>총점 순 정렬 (오름차순)</li>
 * </ul>
 * <p>
 * 정렬은 {@code Comparator} 또는 {@code Comparable}을 사용하여 수행된다.
 * 출력 기능은 포함하지 않으며, 정렬된 데이터는 반환만 한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public interface SortedStudent {

    /**
     * 학생 목록을 이름 기준으로 오름차순 정렬한다.
     *
     * @return 이름 기준으로 정렬된 학생 목록 (HashMap)
     */
    HashMap<String, Student> sortByName();

    /**
     * 학생 목록을 총점 기준으로 오름차순 정렬한다.
     *
     * @return 총점 기준으로 정렬된 학생 목록 (HashMap)
     */
    HashMap<String, Student> sortByTotal();
}
