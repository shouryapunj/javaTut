import java.util.Scanner;
import cal.Calculator;

public class yolo {
    public static void main(String[] args) {
        System.out.println("Spam eggs!");
        Scanner input = new Scanner(System.in);
        char x = 'y';
        while(x == 'y' || x == 'Y') {
            System.out.print("Enter value A and B : ");
            int a = input.nextInt();
            int b = input.nextInt();
            System.out.println("A = " + a + " and B = " + b);
//        char op = System.in.read();
            System.out.print("Enter Operator : ");
            char op = input.next().charAt(0);
//        System.out.println("Char entered : " + op);

            int res = Calculator.calculator(a, b, op);
            System.out.println("A " + op + " B" + " = " + res);
            System.out.println("Again?(y/n)");
            x = input.next().charAt(0);
        }
    }



}
