package basics;

public class FlipACoin {

    enum Coin {
        HEADS, TAILS
    }

    public static void main(String[] args) {
        int length = Coin.values().length;
        System.out.println("flipped a coin ::: " + Coin.values()[(int) (length * Math.random())]);
    }
}
