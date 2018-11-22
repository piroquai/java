public class AdvertisingAgency
{
    public static void main(String[] args) {
        ASMM ivan = new StupidSMM();
        System.out.println(ivan.write("don't worry be happy"));

        ASMM jora = new BadSMM(new StupidSMM());
        System.out.println( jora.write("don't worry be happy!"));

        ASMM albert = new SmartSMM(new StupidSMM());
        System.out.println(albert.write("don't worry be happy"));

        ASMM crazy = new SmartSMM(new BadSMM(new StupidSMM()));
        System.out.println(crazy.write("don't worry be happy"));

        ASMM mark = new MarkSMM(new BadSMM(new StupidSMM()));
        System.out.println(mark.write("don't worry be happy"));



    }
}
