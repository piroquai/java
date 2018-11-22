class Check {
    private String inputString;
    private int stackSize;
    private Stack stack;

    public Check(String inputString) {
        this.inputString = inputString;
        this.stackSize = inputString.length();
        stack = new Stack(stackSize);
    }

    public void makeCheck() {
        try {
            for (int i = 0; i < inputString.length(); i++) {
                char ch = inputString.charAt(i);
                if (ch == '(')
                    stack.push(ch);
                else if (ch == ')')
                    stack.pop();
            }
            if (stack.isEmpty()){
                System.out.println("ОК");
            }
            else {
                System.out.println("Неверно");
            }
        } catch (Exception e) {
            System.out.println("Неверно");
        }
    }
}


