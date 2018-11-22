public class CensorWriter extends DecoratorWriter {
    public CensorWriter(AWriter aWriter) {
        super(aWriter);
    }
    @Override
    public String write(String s){
        return super.write(s).replaceAll("No","Yo, majesty");
    }
}
