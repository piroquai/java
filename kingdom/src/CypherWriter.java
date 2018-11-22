public class CypherWriter extends DecoratorWriter {
        public CypherWriter(AWriter decoratorWriter) {
            super(decoratorWriter);
        }
        @Override
        public String write(String s){
            return super.write(s).replaceAll(" ","");
        }
    }


