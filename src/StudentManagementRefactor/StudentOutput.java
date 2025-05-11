package StudentManagementRefactor;

public interface StudentOutput {
    void output();
    // 출력을 담당하는 기능
    // 출력에는 1.전체 테이블 조회 / 2.학번 조회 후 출력 / 3.성적 순 출력가 있고 가공된 데이터를 출력하기만 하면 된다.
    // 가공된 데이터는 StudentManager에 담길 예정
}
