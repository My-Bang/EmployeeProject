package student.DTO;

public class StudentDTO {
    private String sno;
    private String name;
    private int korean;
    private int english;
    private int math;
    private int science;
    private int total;
    private float average;


    public StudentDTO(String sno, String name, int korean, int english, int math, int science) {
        this.sno = sno;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
        this.science = science;
        this.total = korean + english + math + science;
        this.average = (float) total / 4;
    }

    public String getSno() {
        return sno;
    }

    public String getName() {
        return name;
    }

    public int getKorean() {
        return korean;
    }

    public int getEnglish() {
        return english;
    }

    public int getMath() {
        return math;
    }

    public int getScience() {
        return science;
    }

    public int getTotal() {
        return total;
    }

    public String getGrade() {
        int grade = (int) average / 10;
        if (grade >= 9) return "A";
        else if (grade == 8) return "B";
        else if (grade == 7) return "C";
        else if (grade == 6) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return sno +
                "\t" + name +
                "\t" + korean +
                "\t\t" + english +
                "\t\t" + math +
                "\t\t" + science +
                "\t\t" + average +
                "\t" + total + "\t\t" + getGrade();
    }
}
