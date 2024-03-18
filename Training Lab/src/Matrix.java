public class Matrix {
    private int[][] matrix;
    private int row;
    private int col;

    public Matrix() {
    }

    public Matrix(int[][] matrix) {
        setMatrix(matrix);
        row = matrix.length;
        col = matrix[0].length;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // TODO, you can remove the matrix parameter
    public boolean canMultiplyTwoMatrices(Matrix matrix2) {
        return matrix[0].length == matrix2.getRow();
    }

    // TODO, you can remove the matrix parameter
    public boolean isSquareMatrix() {
        return matrix.length != matrix[0].length;
    }

    // TODO, you can remove the matrix parameter
    private boolean isBoundariesValid(int row, int col) {
        return row >= 0 && row <= matrix.length - 1 && col >= 0 && col <= matrix[0].length - 1;
    }

    // TODO, you can remove the matrix parameter
    public Matrix extractSubmatrix(int deletedRow, int deletedCol) throws ArrayIndexOutOfBoundsException {
        if (!isBoundariesValid(deletedRow, deletedCol)) {
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
        return new Matrix(result);
    }

    public Matrix multiplyByScalar(int number) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                result[row][col] = matrix[row][col] * number;
            }
        }
        return new Matrix(result);
    }


    // TODO, you can  change the parameter int[][] into Matrix Object
    // TODO Optional, you can rename the function to multiply, since it takes only 1 parameter which is its own object, it is clearly that it multiplies 1 matrix with the other

    public Matrix multiplyTwoMatrices(Matrix matrix2) throws IllegalArgumentException {
        if (!canMultiplyTwoMatrices(matrix2)) {
            throw new IllegalArgumentException("The multiplication of the two matrices is not possible");
        }
        int[][] result = new int[matrix.length][matrix2.getCol()];
        int[][] secondMatrix = matrix2.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix2.getCol(); j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    result[i][j] += matrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    public int findDeterminant() throws IllegalArgumentException {
        if (isSquareMatrix()) {
            throw new IllegalArgumentException("Matrix is not square");
        }
        return findDeterminant(new Matrix(getMatrix()));
    }

    // TODO, you can remove the matrix parameter
    private int findDeterminant(Matrix matrix) {

        int[][] result = matrix.getMatrix();
        int determinant = 0;

        if (result.length == 1) {
            return result[0][0];
        }

        if (result.length == 2) {
            return result[0][0] * result[1][1] - result[0][1] * result[1][0];
        }

        for (int col = 0; col < result[0].length; col++) {
            int sign = (col % 2 == 0) ? 1 : -1;
            int cofactor = sign * result[0][col] * findDeterminant(extractSubmatrix(0, col));
            determinant += cofactor;
        }
        return determinant;
    }

    // TODO, no need to send Matrix matrix as the parameter
    public void print() {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}

