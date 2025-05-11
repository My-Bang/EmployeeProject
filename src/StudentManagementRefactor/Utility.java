package StudentManagementRefactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
