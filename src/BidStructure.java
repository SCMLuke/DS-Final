import java.util.HashMap;
import java.util.PriorityQueue;

class BidStructure {
    PriorityQueue<Bid> bidsPriorityQueue;
    HashMap<Integer, Integer> bidderBidsHashMap;
    int currentMinimumBid;

    public BidStructure() {
        bidsPriorityQueue = new PriorityQueue<>((a, b) -> b.bidAmount - a.bidAmount);
        bidderBidsHashMap = new HashMap<>();
        currentMinimumBid = 1;
    }

    public String placeBid(int bidderId, int bidAmount) {
        if (bidAmount <= 0) {
            return "Invalid bid amount";
        }

        if (bidAmount < currentMinimumBid) {
            return "Bid amount must be at least the current minimum bid";
        }

        if (bidderBidsHashMap.containsKey(bidderId)) {
            Bid existingBid = bidsPriorityQueue.stream().filter(b -> b.bidderId == bidderId).findFirst().orElse(null);
            if (existingBid != null) {
                bidsPriorityQueue.remove(existingBid);
                bidderBidsHashMap.put(bidderId, bidAmount);
                Bid newBid = new Bid(bidderId, bidAmount);
                bidsPriorityQueue.offer(newBid);
                return "Bid updated successfully";
            }
        }

        Bid bid = new Bid(bidderId, bidAmount);
        bidsPriorityQueue.offer(bid);
        bidderBidsHashMap.put(bidderId, bidAmount);

        return "Bid placed successfully";
    }

    public String getWinner() {
        if (bidsPriorityQueue.isEmpty()) {
            return "No bids placed";
        }

        Bid highestBid = bidsPriorityQueue.peek();
        int highestBidAmount = highestBid.bidAmount;

//        // Check for tie and restart bid if one is found.
//        if (bidsPriorityQueue.stream().filter(b -> b.bidAmount == highestBidAmount).count() > 1) {
//            currentMinimumBid = highestBidAmount + 1;
//            return "Tie detected. Restarting auction with minimum bid $" + currentMinimumBid;
//        }

        // Check for tie and determine winner based on who submitted first.
        if (bidsPriorityQueue.stream().filter(b -> b.bidAmount == highestBidAmount).count() > 1) {
            int lowestBidderId = Integer.MAX_VALUE;
            for (Bid bid : bidsPriorityQueue) {
                if (bid.bidAmount == highestBidAmount && bid.bidderId < lowestBidderId) {
                    lowestBidderId = bid.bidderId;
                }
            }
            return "Tie detected. The bidder with the lowest ID (" + lowestBidderId + ") wins with bid amount $" + highestBidAmount;
        }

        return "The highest bid is $" + highestBidAmount + " by bidder " + highestBid.bidderId;
    }
}