public class MatrixOperations {

    // TODO, create a class called Matrix, and use it in the parameters, instead of sending the int[][] matrix, Matrix matrix
    // TODO 2, multiplyByScalar can be a method inside the Matrix class, the parameter only takes the scalar number and the matrix class will do the operations and returns the object itself
    public static boolean isValidMatrix(int[][] matrix) {
        return matrix != null && matrix.length != 0 && matrix[0].length != 0;
    }

    public void test(){

    }

    public static boolean isSquareMatrix(int[][] matrix) {
        return matrix.length != matrix[0].length;
    }

    public static boolean isBoundariesValid(int[][] matrix, int row, int col) {
        return row >= 0 && row <= matrix.length - 1 && col >= 0 && col <= matrix[0].length - 1;
    }

    public static boolean canSumTwoMatrices(int[][] matrix1, int[][] matrix2) {
        return matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length;
    }

    public static boolean canMultiplyTwoMatrices(int[][] matrix1, int[][] matrix2) {
        return matrix1[0].length == matrix2.length;
    }

    public static int[][] sumTwoMatrices(int[][] matrix1, int[][] matrix2) {
        if (!canSumTwoMatrices(matrix1, matrix2)) {
            // TODO, check how to create Custom Exceptions
            throw new MatrixOperationsExceptions("The two matrices are not equal in size");
        }
        int[][] result = new int[matrix1.length][matrix1[0].length];
        for (int row = 0; row < matrix1.length; row++) {
            for (int col = 0; col < matrix1[0].length; col++) {
                result[row][col] = matrix1[row][col] + matrix2[row][col];
            }
        }
        return result;
    }

    public static int[][] multiplyByScalar(int[][] matrix, int number) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                result[row][col] = number * matrix[row][col];
            }
        }
        return result;
    }

    public static int[][] transportMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                result[row][col] = matrix[col][row];
            }
        }
        return result;
    }

    public static int[][] multiplyTwoMatrices(int[][] matrix1, int[][] matrix2) {
        if (!canMultiplyTwoMatrices(matrix1, matrix2)) {
            throw new MatrixOperationsExceptions("The multiplication of the two matrices is not possible");
        }
        int[][] result = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] extractSubmatrix(int[][] matrix, int deletedRow, int deletedCol) {
        if (!isBoundariesValid(matrix, deletedRow, deletedCol)) {
            throw new ArrayIndexOutOfBoundsException("Invalid row or column index");
        }
        int[][] result = new int[matrix.length - 1][matrix[0].length - 1];
        for (int matrixRow = 0, resultRow = 0; matrixRow < matrix.length; matrixRow++) {
            if (matrixRow == deletedRow) {
                continue;
            }
            for (int matrixCol = 0, resultCol = 0; matrixCol < matrix[0].length; matrixCol++) {
                if (matrixCol == deletedCol) {
                    continue;
                }
                result[resultRow][resultCol] = matrix[matrixRow][matrixCol];
                resultCol++;
            }
            resultRow++;
        }
        return result;
    }

    public static int[][] makeDiagonalMatrix(int[][] squareMatrix) {
        if (isSquareMatrix(squareMatrix)) {
            throw new MatrixOperationsExceptions("Matrix is not square");
        }
        int[][] result = new int[squareMatrix.length][squareMatrix[0].length];
        for (int row = 0; row < squareMatrix.length; row++) {
            for (int col = 0; col < squareMatrix[0].length; col++) {
                result[row][col] = (row == col) ? squareMatrix[row][col] : 0;
            }
        }
        return result;
    }

    public static int[][] makeLowerTriangularMatrix(int[][] squareMatrix) {
        if (isSquareMatrix(squareMatrix)) {
            throw new MatrixOperationsExceptions("Matrix is not square");
        }
        int[][] result = new int[squareMatrix.length][squareMatrix[0].length];
        for (int row = 0; row < squareMatrix.length; row++) {
            System.arraycopy(squareMatrix[row], 0, result[row], 0, row + 1);
        }
        return result;
    }


    public static int[][] makeUpperTriangularMatrix(int[][] squareMatrix) {
        if (isSquareMatrix(squareMatrix)) {
            throw new MatrixOperationsExceptions("Matrix is not square");
        }
        int[][] result = new int[squareMatrix.length][squareMatrix[0].length];
        for (int row = 0; row < squareMatrix.length; row++) {
            System.arraycopy(squareMatrix[row], row, result[row], row, squareMatrix.length - row);
        }
        return result;
    }

    public static int findDeterminant(int[][] squareMatrix) {
        if (isSquareMatrix(squareMatrix)) {
            throw new MatrixOperationsExceptions("Matrix is not square");
        }

        int determinant = 0;

        if (squareMatrix.length == 1) {
            return squareMatrix[0][0];
        }

        if (squareMatrix.length == 2) {
            return squareMatrix[0][0] * squareMatrix[1][1] - squareMatrix[0][1] * squareMatrix[1][0];
        }

        for (int col = 0; col < squareMatrix[0].length; col++) {
            int sign = (col % 2 == 0) ? 1 : -1;
            int cofactor = sign * squareMatrix[0][col] * findDeterminant(extractSubmatrix(squareMatrix, 0, col));
            determinant += cofactor;
        }
        return determinant;
    }
}





