public class DecoratorWriter extends StupidWriter {
protected AWriter decoratedWriter;

    public DecoratorWriter(AWriter decoratedWriter) {
        this.decoratedWriter = decoratedWriter;
    }

    @Override
    public String write(String s){
    return decoratedWriter.write(s);
}
}
