public class Main {

    public static void main(String[] args) {

        double[][] a = {
                {2, -1, 0},
                {-1, 1, 4},
                {1, 2, 3}
        };
        double[] f = {0, 13, 14};

        Gauss test = new Gauss();
        test.setA(a);
        test.setF(f);
        test.setN(a);

        try{
            test.findAnswers();
        }
        catch(MyExceptions ex){
            System.out.println(ex.getMessage());
        }

    }
}
