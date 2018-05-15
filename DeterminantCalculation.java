public class DeterminantCalculation {

    public double determinantCalculation(double[][] matrix) {
        double determinant = 0;

        if (matrix.length == 2){
            determinant = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        }
        else {
            int koeff = 1;
            for(int i = 0; i < matrix.length; i++){
                if(i%2 == 1){  //я решил не возводить в степень, а просто поставить условие - это быстрее. Т.к. я раскладываю всегда по первой (читай - "нулевой") строке, то фактически я проверяю на четность значение i+0.
                    koeff = -1;
                }
                else{
                    koeff = 1;
                };
                //собственно разложение:
                determinant += koeff * matrix[0][i] * determinantCalculation(GetMinor(matrix,0, i));
            }
        }

        return determinant;
    }

    private double[][] GetMinor(double[][] matrix, int row, int column){
        int minorLength = matrix.length - 1;
        double[][] minor = new double[minorLength][minorLength];
        int dI = 0;//эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
        int dJ = 0;
        for(int i = 0; i <= minorLength; i++){
            dJ = 0;
            for(int j = 0; j <= minorLength; j++){
                if(i == row){
                    dI = 1;
                }
                else{
                    if(j == column){
                        dJ = 1;
                    }
                    else{
                        minor[i-dI][j-dJ] = matrix[i][j];
                    }
                }
            }
        }

        return minor;

    }
}
