package StudentManagementRefactor;

public interface SearchStudent extends StudentOutput{
    Student search(String sno);
    // 학번으로 조회 하는 기능
    // 출력은 담당하지 않는다.
}
