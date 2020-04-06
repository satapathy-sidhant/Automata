package basics;

public class LeapYear {

    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]);
        System.out.println("Is " + year + " a leap year? " + ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)));
    }
}
