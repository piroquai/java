package university;

public class Teacher
{
    String name;
    Subject subject;
    public Teacher(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }


    void talk (Student student) {

        //100=5, 50=3, 10=1

        student.mark= (int) (student.abilityToStudy*subject.data);
    }

    public String comment() {
        return name + ", " + subject;
    }
}
