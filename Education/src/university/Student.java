package university;

public class Student {
    String name;
    int course;
    int mark;

    public Student(String name, int course, int mark, float abilityToStudy) {
        this.name = name;
        this.course = course;
        this.mark = mark;
        this.abilityToStudy = abilityToStudy;
    }

    float abilityToStudy;
    void listen(Teacher teacher){}

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course=" + course +
                ", mark=" + mark +
                ", abilityToStudy=" + abilityToStudy +
                '}';
    }


}
