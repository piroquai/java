public class DecoratorSMM extends StupidSMM {
protected ASMM decoratedWriter;

    public DecoratorSMM(ASMM decoratedWriter) {
        this.decoratedWriter = decoratedWriter;
    }

    @Override
    public String write(String s){
    return decoratedWriter.write(s);
}
}
