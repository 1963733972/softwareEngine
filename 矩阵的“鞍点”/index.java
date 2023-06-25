public class SaddlePoint {
    
    public static int[] findSaddlePoint(int[][] matrix) {
        int[] saddlePoint = new int[2];
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            int minRow = matrix[i][0];
            int colIndex = 0;
            // 找到该行的最小值和对应列的下标
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] < minRow) {
                    minRow = matrix[i][j];
                    colIndex = j;
                }
            }
            boolean isSaddlePoint = true;
            // 判断该列上的元素是否都小于等于该行的最小值
            for (int k = 0; k < rows; k++) {
                if (matrix[k][colIndex] > minRow) {
                    isSaddlePoint = false;
                    break;
                }
            }
            if (isSaddlePoint) {
                saddlePoint[0] = i;
                saddlePoint[1] = colIndex;
                return saddlePoint;
            }
        }

        return saddlePoint;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[] saddlePoint = findSaddlePoint(matrix);
        if (saddlePoint[0] == 0 && saddlePoint[1] == 0) {
            System.out.println("No saddle point found.");
        } else {
            System.out.println("The saddle point is at (" + saddlePoint[0] + ", " + saddlePoint[1] + ").");
        }
    }
}