import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {

    public static List<Integer> lowestK(
            List<Integer> prices,
            int k) {

        List<Integer> result =
                new ArrayList<Integer>();

        if (prices == null || k <= 0) {
            return result;
        }

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<Integer>(
                        Collections.reverseOrder()
                );

        for (int i = 0; i < prices.size(); i++) {

            Integer price = prices.get(i);

            if (price == null || price < 0) {
                continue;
            }

            if (maxHeap.size() < k) {

                maxHeap.offer(price);

            } else if (price < maxHeap.peek()) {

                maxHeap.poll();
                maxHeap.offer(price);
            }
        }

        result.addAll(maxHeap);

        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {

        List<Integer> prices =
                new ArrayList<Integer>();

        prices.add(500);
        prices.add(120);
        prices.add(null);
        prices.add(300);
        prices.add(-50);
        prices.add(80);
        prices.add(200);
        prices.add(100);

        System.out.println(
                lowestK(prices, 3)
        );

        System.out.println(
                lowestK(prices, 5)
        );

        System.out.println(
                lowestK(prices, 0)
        );
    }
}