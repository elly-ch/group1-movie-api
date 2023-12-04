package sg.edu.ntu.simplecrm.services;

public class DemoService {
    
    public int add(int a, int b) {
        return a + b;
    }

    public int substract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
    
    public int divide(int a, int b) {
        return a / b;
    }
    
    public boolean isEven(int a) {
        return a % 2 == 0;
    }
}
