package university;

public class UniversityClass {
    public static void main(String[] args) {
        Subject javaLanguage = new Subject("Java Language Course", 5);
        Teacher VladimirVladimirovich = new Teacher("Ivanov Vladimir Vladimirovich ", javaLanguage);
        Student vasia = new Student(
                "Vasya",
                3,
                0,
                0.5f);

        VladimirVladimirovich.talk(vasia);
        System.out.println("Teacher says:" + VladimirVladimirovich.comment());
        System.out.println("Student says:" + vasia);
    }
}
