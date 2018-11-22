public class MarkSMM extends DecoratorSMM {
    public MarkSMM(ASMM decoratorWriter) {
        super(decoratorWriter);
    }
    @Override
    public String write(String s){
        return super.write(s).replaceAll("be","###be###");
    }
}
