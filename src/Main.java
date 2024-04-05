import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BidStructure bid = new BidStructure();

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt(1000) + 1;
            System.out.println(bid.placeBid(i, randomNumber));
        }
        System.out.println(bid.getWinner());

        // Auction repeated with new lowest bid until winner found.

//        System.out.println(bid.placeBid(1, 100));
//        System.out.println(bid.placeBid(2, 120));
//        System.out.println(bid.placeBid(3, 120));
//
//        System.out.println(bid.getWinner());
//
//        System.out.println(bid.placeBid(1, 100));
//        System.out.println(bid.placeBid(2, 122));
//        System.out.println(bid.placeBid(3, 123));
//
//        System.out.println(bid.getWinner());
    }
}