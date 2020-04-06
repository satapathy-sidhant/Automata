package basics;

public class Binary {

    public static void main(String[] args) {
        int number = 106;
        int power = 1;
        while (power <= number / 2) {
            power = power * 2;
        }
        System.out.println(power);
        while (power > 0) {
            if (number >= power) {
                number = number - power;
                System.out.print(1);
            } else {
                System.out.print(0);
            }
            power = power / 2;
        }
    }
}
