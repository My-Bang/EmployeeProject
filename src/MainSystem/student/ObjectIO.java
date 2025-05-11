package MainSystem.student;

import java.io.IOException;
import java.util.Map;

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
public abstract class ObjectIO<K, V> {
    // 기존: public abstract HashMap<String, Student> loadData() throws IOException;
    // 변경: Map<K, V>를 반환하도록 수정 (유연성을 위해)
    public abstract Map<K, V> loadData() throws IOException;

    // 기존에는 saveData() 메서드가 없었으므로, JSON 저장 기능을 추가했습니다.
    public abstract void saveData(Map<K, V> data) throws IOException;
}
