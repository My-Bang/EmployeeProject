package StudentManagementRefactor;

public interface StudentIO extends StudentInput, SortedStudent, SearchStudent, DeleteStudent {

    //다중 상속을 하기 위한 징검다리 역할 인터페이스
    // StudentIO를 StudentDBIO이 구현 하게.
}
