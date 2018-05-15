import com.sun.org.apache.xalan.internal.lib.Extensions;

public class Gauss extends DeterminantCalculation {

    public void setA(double[][] a) {
        this.a = a;
    }

    public void setF(double[] f) {
        this.f = f;
    }

    // !!! Find info about "static" keyword usage
    // ??? Why do I need to use static keyword in this case? (maybe coz elements are initialized here)
    /*static double[][] a  = {
            {2, -1, 0},
            {-1, 1, 4},
            {1, 2, 3}
    };*/

    public void setN(double a[][]) {
        this.n = a.length;
    }

    private int n;
    // I planned to use n during creating arrays, but it wasn't work
    // !! Find info about it!!
    private double[][] a = new double[3][3];
    private double[] f = new double[3];
    private double[] x = new double[3];
    private double[] f_n = new double[3];
    private double[][] a_n = new double[3][3];
    private double[][] b = new double[3][3];
    private double[] g = new double[3];

    public void findAnswers() throws MyExceptions{
        /*if(isMatrixEqualToZero(f) && determinantCalculation(a) == 0){
            System.out.println("Система имеет тривиальное решение = 0 и множество нетривиальных решений.");
        }
        else if (isMatrixEqualToZero(f) && determinantCalculation(a) != 0){
            System.out.println("Система имеет единственное решение = 0.");
        }
        else if (!isMatrixEqualToZero(f) && determinantCalculation(a) == 0){
            System.out.println("Система либо не имеет решений, либо имеет множество.");
        }
        else {
            this.directProcess();
            this.revertProcess();
        }*/
        if(isMatrixEqualToZero(f) && determinantCalculation(a) == 0)
            throw new MyExceptions("Система имеет тривиальное решение = 0 и множество нетривиальных решений.");
        if (isMatrixEqualToZero(f) && determinantCalculation(a) != 0)
            throw new MyExceptions("Система имеет единственное решение = 0.");
        if (!isMatrixEqualToZero(f) && determinantCalculation(a) == 0)
            throw new MyExceptions("Система либо не имеет решений, либо имеет множество.");

        this.directProcess();
        this.revertProcess();

    }

    private boolean isMatrixEqualToZero (double[] matrix){
        boolean equalsZero = true;
        for (double element: matrix) {
            if (element != 0)
                equalsZero = false;
        }

        return equalsZero;
    }


    private void directProcess() {

        for (int divisionNumber = 0; divisionNumber < n; divisionNumber++){
            g[divisionNumber] = f[divisionNumber] / a[divisionNumber][divisionNumber];
            //System.out.println("g[" + divisionNumber + "] = " + g[divisionNumber]); //These values are printed to console just for my convenience (to understand if it works correctly)

            // !!! Maybe it'll be better to change initial value of j (coz result will be equal to 0.00 for some x)
            for (int j = 0; j < n; j++) {
                b[divisionNumber][j] = a[divisionNumber][j] / a[divisionNumber][divisionNumber];
                //System.out.println("b[" + divisionNumber + "][" + j + "] = " + b[divisionNumber][j]); //These values are printed to console just for my convenience (to understand if it works correctly)
            }

            for (int i = divisionNumber + 1; i < n; i++) {
                f_n[i] = f[i] - g[divisionNumber] * a[i][divisionNumber];
                //System.out.println("f_" + divisionNumber + "[" + i + "] = " + f_n[i]); //These values are printed to console just for my convenience (to understand if it works correctly)
            }

            for (int i = divisionNumber + 1; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a_n[i][j] = a[i][j] - b[divisionNumber][j] * a[i][divisionNumber];
                    //System.out.println("a_" + divisionNumber + "[" + i + "][" + j + "] = " + a_n[i][j]); //These values are printed to console just for my convenience (to understand if it works correctly)
                }
            }

            // ??? Why does this method copies elements at time when some element of a_n is changed? (not on this line but during executing the code above)
            //System.arraycopy(a_n, 0, a, 0, a_n.length);

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    a[i][j] = a_n[i][j];
                }
            }
            for (int i = 0; i < n; i++) {
                f[i] = f_n[i];
            }
        }
    }

    private void revertProcess(){

        double sum = 0;
        for(int i = n - 1; i > -1; i--){

            for (int j = i + 1; j < n; j++){
                sum += b[i][j]*x[j];
            }
            x[i] = g[i] - sum;
            //System.out.println("x[" + i + "] = " + x[i]);
            sum = 0;
        }
        for (int i = 0; i < n; i++) {
            System.out.println("x[" + (i + 1) + "] = " + x[i]);
        }
    }
}
