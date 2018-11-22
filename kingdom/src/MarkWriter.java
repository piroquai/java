public class MarkWriter extends DecoratorWriter {
    public MarkWriter(AWriter decoratorWriter) {
        super(decoratorWriter);
    }
    @Override
    public String write(String s){
        return super.write(s).replaceAll("woman","###woman###");
    }
}
