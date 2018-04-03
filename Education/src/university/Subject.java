package university;

public class Subject {
    String name;
    int data;

    public Subject(String name, int data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
