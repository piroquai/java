public class BadSMM extends DecoratorSMM {
    public BadSMM(ASMM asmm) {
        super(asmm);
    }
    @Override
    public String write(String s){
        return super.write(s).replaceAll("happy","sad");
    }
}
