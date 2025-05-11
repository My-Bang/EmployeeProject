package MainSystem.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 사용자 입력을 처리하는 유틸리티 클래스.
 * <p>
 * 콘솔에서 사용자 입력을 받아 다양한 데이터 타입(String, Integer, Double, Float)으로 변환하는 기능을 제공한다.
 * </p>
 *
 * @author 작성자명
 * @version 1.0
 * @since 2024-02-16
 */
public class Utility {

    /** 사용자 입력을 읽기 위한 BufferedReader 객체 */
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * 사용자 입력을 받아 지정된 타입으로 변환하는 메서드.
     * <p>
     * 지원하는 데이터 타입:
     * <ul>
     *     <li>{@code String}</li>
     *     <li>{@code Integer}</li>
     *     <li>{@code Double}</li>
     *     <li>{@code Float}</li>
     * </ul>
     * </p>
     * <p>
     * 입력값이 올바르지 않으면 예외를 처리하고 다시 입력받도록 한다.
     * </p>
     *
     * @param type 변환할 데이터 타입 (예: {@code String.class}, {@code Integer.class})
     * @param <T> 제네릭 타입 (입력받을 데이터 타입)
     * @return 변환된 입력값 (입력값이 유효하지 않으면 다시 입력 요청)
     */
    public static <T> T readInput(Class<T> type) {
        while (true) {
            try {
                String input = br.readLine().trim(); // 공백 제거 후 입력 받기

                if (type == String.class) {
                    return type.cast(input);
                } else if (type == Integer.class) {
                    return type.cast(Integer.parseInt(input));
                } else if (type == Double.class) {
                    return type.cast(Double.parseDouble(input));
                } else if (type == Float.class) {
                    return type.cast(Float.parseFloat(input));
                } else {
                    System.out.println("지원하지 않는 타입입니다.");
                    return null;
                }
            } catch (IOException e) {
                System.out.println("입력 오류가 발생했습니다. 다시 시도하세요.");
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해야 합니다. 다시 입력하세요.");
            } catch (NullPointerException e) {
                System.out.println("입력값이 비어 있습니다. 다시 입력하세요.");
            }
        }
    }
}
