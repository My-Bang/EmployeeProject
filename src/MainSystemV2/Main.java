package MainSystemV2;

import MainSystemV2.controller.EmployeeController;
import MainSystemV2.model.Manager;
import MainSystemV2.model.Secretary;
import MainSystemV2.model.Staff;
import MainSystemV2.student.Student;
import MainSystemV2.student.StudentManager;
import MainSystemV2.student.Utility;
import MainSystemV2.view.EmployeeView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        while (true) {
            System.out.println("메인 시스템입니다.");
            System.out.println("1. 직원 시스템");
            System.out.println("2. 학생 시스템");
            System.out.println("3. 시스템 종료");
            System.out.print("원하는 번호를 입력하세요: ");

            int mainChoice = Utility.readInput(Integer.class);
            switch (mainChoice) {
                case 1:
                    employeeSystem();
                    break;
                case 2:
                    studentSystem();
                    break;
                case 3:
                    System.out.println("시스템을 종료합니다.");
                    return; // 메인 메서드 종료 → 프로그램 종료
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 선택하세요.");
            }
        }
    }

    // 직원 시스템: 사용자가 "0"을 입력하면 해당 시스템 종료 후 메인 메뉴로 복귀
    private static void employeeSystem() throws SQLException, ClassNotFoundException {
        EmployeeView view = new EmployeeView();
        EmployeeController controller = new EmployeeController(view);
        controller.updateSalary();

        while (true) {
            System.out.println("\n--- 직원 시스템 ---");
            System.out.println("1. 입력");
            System.out.println("2. 전체 조회");
            System.out.println("3. 사번으로 조회");
            System.out.println("4. 이름으로 조회");
            System.out.println("5. 직군별 검색");
            System.out.println("0. 직원 시스템 종료");
            System.out.print("원하는 번호를 입력하세요: ");

            int input = Utility.readInput(Integer.class);

            switch (input) {
                case 1: // 직원 입력
                    System.out.println("직원 번호를 입력하세요.");
                    String eno = getValidatedNumber();

                    System.out.println("직원 이름을 입력하세요.");
                    String name = getValidatedName();

                    System.out.println("입사년도를 입력하세요.");
                    int enterYear = getValidateEnterYear();
                    System.out.println("입사월을 입력하세요.");
                    int enterMonth = getValidateEnterMonth();
                    System.out.println("입사일을 입력하세요.");
                    int enterDay = getValidateEnterDay(enterYear, enterMonth);
                    System.out.println("월급을 입력하세요.");
                    int salary = Utility.readInput(Integer.class);

                    System.out.println("직군을 선택하세요");
                    System.out.println("1. 직원, 2. 임원, 3. 비서");
                    int role = Utility.readInput(Integer.class);

                    // 현재 날짜 가져오기
                    LocalDate currentDate = LocalDate.now();
                    // 입사 날짜 객체 생성
                    LocalDate enterDate = LocalDate.of(enterYear, enterMonth, enterDay);
                    long daysWorked = ChronoUnit.DAYS.between(enterDate, currentDate);
                    // 근속 연수 계산 (연 단위 차이 계산)
                    int lastRaiseYear = (int) (daysWorked / 365);

                    switch (role) {
                        case 1:
                            controller.addEmployee(new Staff(eno, name, enterYear, enterMonth, enterDay, salary, lastRaiseYear));
                            break;
                        case 2:
                            System.out.println("비서의 직원 번호를 입력하세요.");
                            String secno = Utility.readInput(String.class);
                            controller.addEmployee(new Manager(eno, name, enterYear, enterMonth, enterDay, secno, salary, lastRaiseYear));
                            break;
                        case 3:
                            controller.addEmployee(new Secretary(eno, name, enterYear, enterMonth, enterDay, salary, lastRaiseYear));
                            break;
                        default:
                            System.out.println("잘못 선택하셨습니다.");
                            break;
                    }
                    break;
                case 2: // 전체 조회
                    controller.updateSalary();
                    controller.listAllEmployees();
                    break;
                case 3: // 사번으로 조회
                    System.out.println("조회하고자 하는 사번을 입력하세요.");
                    String employeeNum = Utility.readInput(String.class);
                    controller.getEmployeeById(employeeNum);
                    break;
                case 4: // 이름으로 조회
                    System.out.println("조회하고자 하는 직원의 이름을 입력하세요.");
                    String employeeName = Utility.readInput(String.class);
                    controller.searchEmployeeByName(employeeName);
                    break;
                case 5: // 직군별 검색
                    System.out.println("조회하고자 하는 직군을 선택하세요.");
                    System.out.println("1. 직원, 2. 임원, 3. 비서");
                    String employeeRole = Utility.readInput(String.class);
                    switch (employeeRole) {
                        case "1":
                            controller.searchEmployeesByRole("Staff");
                            break;
                        case "2":
                            controller.searchEmployeesByRole("Manager");
                            break;
                        case "3":
                            controller.searchEmployeesByRole("Secretary");
                            break;
                        default:
                            System.out.println("잘못 입력하셨습니다.");
                    }
                    break;
                case 0: // 직원 시스템 종료 후 메인 메뉴로 복귀
                    System.out.println("직원 시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 학생 시스템: 사용자가 "0"을 입력하면 해당 시스템 종료 후 메인 메뉴로 복귀
    private static void studentSystem() {
        StudentManager.getInstance().loadData();

        while (true) {
            System.out.println("\n--- 학생 시스템 ---");
            System.out.println("1. 입력");
            System.out.println("2. 전체 조회");
            System.out.println("3. 학번으로 조회");
            System.out.println("4. 정렬(이름 순)");
            System.out.println("5. 정렬(성적 순)");
            System.out.println("6. 삭제");
            System.out.println("0. 학생 시스템 종료");
            System.out.print("원하는 번호를 입력하세요: ");

            int input = Utility.readInput(Integer.class);

            switch (input) {
                case 1: // 학생 입력
                    String sno = getValidatedNumber();
                    String name = getValidatedName();

                    int korean = getValidatedScore("국어");
                    int english = getValidatedScore("영어");
                    int math = getValidatedScore("수학");
                    int science = getValidatedScore("과학");

                    int total = korean + english + math + science;
                    double average = (double) total / 4;
                    String grade = calculateGrade(average);

                    Student student = new Student(sno, name, korean, english, math, science, total, average, grade);
                    StudentManager.getInstance().input(student); // JSON 파일에 데이터 입력
                    break;
                case 2: // 전체 조회
                    StudentManager.getInstance().loadData();
                    StudentManager.getInstance().output();
                    break;
                case 3: // 특정 학생 조회
                    System.out.println("조회하고자 하는 학번을 입력하세요.");
                    String searchKey = getValidatedNumber();
                    StudentManager.getInstance().search(searchKey);
                    break;
                case 4: // 이름 기준 정렬
                    StudentManager.getInstance().sortByName();
                    StudentManager.getInstance().output();
                    break;
                case 5: // 성적 기준 정렬
                    StudentManager.getInstance().sortByTotal();
                    StudentManager.getInstance().output();
                    break;
                case 6: // 학생 삭제
                    System.out.println("삭제하고자 하는 학번을 입력하세요.");
                    String deleteKey = getValidatedNumber();
                    StudentManager.getInstance().deleteStudent(deleteKey);
                    StudentManager.getInstance().output();
                    break;
                case 0: // 학생 시스템 종료 후 메인 메뉴로 복귀
                    System.out.println("학생 시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 아래는 입력값 검증을 위한 보조 메서드들

    private static String getValidatedNumber() {
        System.out.println("5자리 숫자를 입력하세요.");
        while (true) {
            String sno = Utility.readInput(String.class);
            if (sno.matches("\\d{5}")) {
                return sno;
            }
            System.out.println("반드시 5자리 숫자로 입력해야 합니다. 다시 입력하세요.");
        }
    }

    private static int getValidateEnterYear() {
        System.out.println("1950년 부터 2025 년 까지 입력 가능합니다.");
        while (true) {
            String input = Utility.readInput(String.class);
            if (input.matches("\\d{4}")) {
                int year = Integer.parseInt(input);
                if (year >= 1950 && year <= 2025) { // 현실적인 범위 체크
                    return year;
                }
            }
            System.out.println("올바른 연도를 입력하세요. 예: 2020");
        }
    }

    public static int getValidateEnterMonth() {
        System.out.println("1부터 12까지 입력 가능합니다.");
        while (true) {
            String input = Utility.readInput(String.class);
            if (input.matches("^(0?[1-9]|1[0-2])$")) { // 01~09 또는 1~12
                int month = Integer.parseInt(input);
                return month;
            }
            System.out.println("올바른 월을 입력하세요 (1~12)");
        }
    }

    public static int getValidateEnterDay(int year, int month) {
        while (true) {
            String input = Utility.readInput(String.class);
            if (input.matches("^0?[1-9]$|^[12][0-9]$|^3[01]$")) { // 01~09, 10~29, 30, 31 허용
                int day = Integer.parseInt(input);
                if (day <= getDaysInMonth(year, month)) {
                    return day;
                }
            }
            System.out.println("올바른 일을 입력하세요. (" + month + "월의 최대 일수: " + getDaysInMonth(year, month) + ")");
        }
    }

    private static int getDaysInMonth(int year, int month) {
        if (month == 2) { // 2월이면 윤년 체크
            return isLeapYear(year) ? 29 : 28;
        }
        return switch (month) {
            case 4, 6, 9, 11 -> 30; // 30일까지 있는 달 (4, 6, 9, 11월)
            default -> 31; // 31일까지 있는 달 (1, 3, 5, 7, 8, 10, 12월)
        };
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }


    private static int getValidatedScore(String subject) {
        System.out.println(subject + " 점수를 입력하세요 (0~100):");
        while (true) {
            int score = Utility.readInput(Integer.class);
            if (score >= 0 && score <= 100) {
                return score;
            }
            System.out.println("점수는 0에서 100 사이로 입력해야 합니다. 다시 입력하세요.");
        }
    }

    private static String getValidatedName() {
        System.out.println("이름을 입력하세요 (한글만 가능):");
        while (true) {
            String name = Utility.readInput(String.class);
            if (name.matches("[가-힣]+")) {
                return name;
            }
            System.out.println("이름은 한글만 입력해야 합니다. 다시 입력하세요.");
        }
    }

    private static String calculateGrade(double average) {
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }
}
