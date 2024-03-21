package com.progressoft.lamda;

public class AdditionCalculator {


    private int x = 50;
    private int y = 200;


    double addNumbers(Calculator calculator){
        x  = x * 2;
        y = y * 5;
        if(x > 50){
            System.out.println("Warning: x is bigger than 50");
        }
        return calculator.doCalculate(x, y);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
