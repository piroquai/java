public class SmartSMM extends DecoratorSMM {
        public SmartSMM(ASMM decoratorWriter) {
            super(decoratorWriter);
        }
        @Override
        public String write(String s){
            return super.write(s).replaceAll(" "," like ");
        }
    }


