package MainSystem.student;

/**
 * 학생 데이터 관리 기능을 통합하는 인터페이스.
 * <p>
 * 이 인터페이스는 여러 개의 기능 인터페이스를 상속하여 학생 정보를 관리하는
 * 통합된 구조를 제공한다. {@code StudentDBIO}에서 구현되며,
 * 데이터 입력, 검색, 정렬, 삭제 기능을 포함한다.
 * </p>
 *
 * <p>
 * 포함된 기능:
 * <ul>
 *     <li>{@link StudentInput} - 학생 데이터 입력</li>
 *     <li>{@link SortedStudent} - 학생 정렬 (이름, 총점 기준)</li>
 *     <li>{@link SearchStudent} - 학번을 이용한 학생 검색</li>
 *     <li>{@link DeleteStudent} - 학번을 이용한 학생 정보 삭제</li>
 * </ul>
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public interface StudentIO extends StudentInput, SortedStudent, SearchStudent, DeleteStudent {
    // 다중 상속을 하기 위한 인터페이스
    // StudentIO를 StudentDBIO가 구현하여 사용한다.
}
