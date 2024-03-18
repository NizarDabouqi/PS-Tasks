import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        int[][] array1 =
                {{1, 3},
                        {5, 0}};

        int[][] array2 =
                {{1, 2, 3},
                        {4, 5, 6}};

        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix (array2);
        Matrix resultMatrix = new Matrix();

        matrix1 = matrix1.multiplyByScalar(2);
        resultMatrix = matrix1.multiplyTwoMatrices(matrix2);
        resultMatrix.print();


        int determinant = matrix1.findDeterminant();
        System.out.println();
        System.out.println("Determinant = "+ determinant);



        //int sum = MatrixOperations.findDeterminant(array1);

        double n1 =  55.5;
        double n12 =  53.541252131254123;
        System.out.println(n1 + n12);


        BigDecimal b1 = new BigDecimal("55.5");
        BigDecimal b2 = new BigDecimal("53.541252131254123");

        System.out.println(b1.add(b2));


            MatrixOperations.multiplyTwoMatrices(matrix1.getMatrix(), matrix2.getMatrix());


    }
}

