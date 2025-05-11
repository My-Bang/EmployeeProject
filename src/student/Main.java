package student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n학생 관리 메뉴:");
            System.out.println("1. 학생 정보 입력");
            System.out.println("2. 학생 정보 출력 (전체 출력 또는 검색)");
            System.out.println("3. 학생 삭제");
            System.out.println("4. 학생 정렬 (총점 기준)");
            System.out.println("5. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.input();
                    break;
                case 2:
                    manager.print();
                    break;
                case 3:
                    manager.delete();
                    break;
                case 4:
                    manager.sort();
                    break;
                case 5:
                    System.out.println("**********************************************************************************************************");
                    System.out.println("                                                   ##  프로그램 종료 ##                                                   ");
                    System.out.println("**********************************************************************************************************");                    return;
                default:
                    System.out.println("잘못된 입력");
            }
        }
    }
}
