package com.progressoft.lamda;

public class LambdaMain {


    public static void main(String[] args) {
        AdditionCalculator additionCalculator = new AdditionCalculator();


        double myNumber = additionCalculator.addNumbers(
                (x, y) ->  { return x + y;}
        );

        System.out.println(myNumber);

    }

}
