package cal;
public class Calculator {

    public static int calculator(int x, int y, char z) {
        switch (z) {
            case '+' :return x + y;
            case '-' :return x - y;
            case '*' :return x * y;
            case '/' :if (y == 0) {
                System.out.println("Divide by zero error!");
                return 0 ;

                //return System.out.println("Divide by zero error!");
            } else return x / y;
            default:
                return 0;
            //return System.out.println("Enter valid operator!");
        }
    }

}
