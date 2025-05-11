package MainSystem.student;

import java.util.HashMap;

/**
 * 학생 정보를 삭제하는 기능을 정의하는 인터페이스.
 * <p>
 * 특정 학번({@code sno})을 기준으로 JSON 파일에서 해당 학생 데이터를 제거하고,
 * 삭제 후 변경된 학생 목록을 반환하는 기능을 제공한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public interface DeleteStudent {

    /**
     * 학번({@code sno})을 기준으로 JSON 파일에서 학생 정보를 삭제하고,
     * 삭제 후 최신 학생 목록을 반환한다.
     *
     * @param sno 삭제할 학생의 학번 (Primary Key 역할)
     * @return 삭제된 후의 학생 목록 (HashMap)
     */
    HashMap<String, Student> deleteStudent(String sno);
}
