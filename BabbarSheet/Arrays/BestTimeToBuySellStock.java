package BabbarSheet.Arrays;

public class BestTimeToBuySellStock {

    // Approach I
    public int maxProfit1(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    // Approach II
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    // Approach III
    public int maxProfit3(int[] arr) {
        int n = arr.length;
        int maxProfitFront = 0;
        int maxProfitBack = 0;
        int minFront = arr[0];
        int maxBack = arr[n - 1];
        int[] t = new int[n];
        int ans = 0;
        t[0] = 0;
        for (int i = 1; i < n; i++) {
            maxProfitFront = Math.max(maxProfitFront, arr[i] - minFront);
            minFront = Math.min(minFront, arr[i]);
            t[i] = maxProfitFront;
        }
        for (int i = n - 2; i >= 0; i--) {
            maxProfitBack = Math.max(maxProfitBack, maxBack - arr[i]);
            maxBack = Math.max(maxBack, arr[i]);
            t[i] += maxProfitBack;
            ans = Math.max(ans, t[i]);
        }
        return ans;
    }
}
