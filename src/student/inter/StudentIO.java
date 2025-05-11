package student.inter;

public interface StudentIO extends StudentInput,SearchStudent,SortedStudent {
   @Override
    void input();
    @Override
    void print();
    @Override
    void search();
    @Override
    void sort();

}