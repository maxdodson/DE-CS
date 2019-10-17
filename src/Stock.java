import java.util.PriorityQueue;
public class Stock {
   private static java.text.DecimalFormat money;
   private String symbol;
   private String name;
   private double startingPrice;
   private double lowPrice;
   private double highPrice;
   private double lastPrice;
   private int volume;
   private PriorityQueue<TradeOrder> sellOrders;
   private PriorityQueue<TradeOrder> buyOrders;
   
   public Stock(String symbol, String name, double price) {
      this.symbol = symbol;
      this.name = name;
      startingPrice = lowPrice = highPrice = lastPrice = price;
      volume = 0;
      sellOrders = new PriorityQueue<TradeOrder>(11, new PriceComparator());
      buyOrders = new PriorityQueue<TradeOrder>(11, new PriceComparator(false));
   }
   
   public String getQuote() {
      String msg = String.format("%s (%s)\nPrice: %.2f hi: %.2f lo: %.2f vol: %d\nAsk: ", name, symbol, lastPrice, highPrice, lowPrice, volume);
      if (sellOrders.peek().isMarket())
         msg += "market ";
      else
         msg += String.format("%.2f ", sellOrders.peek().getPrice());
      
      msg += "size ";
      
      if (sellOrders.size() == 0)
         msg += "none";
      else
         msg += sellOrders.peek().getShares();
         
      msg += "Bid: ";
      
      if (buyOrders.peek().isMarket())
         msg += "market ";
      else
         msg += String.format("%.2f ", buyOrders.peek().getPrice());
      
      msg += "size ";
      
      if (buyOrders.size() == 0)
         msg += "none";
      else
         msg += buyOrders.peek().getShares();
         
      return msg;
         
   }
   
   public void placeOrder(TradeOrder order) {
      String msg = "New Order: ";
      if (order.isSell()) {
         sellOrders.add(order);
         msg += "Sell ";
      } else if (order.isBuy()) {
         buyOrders.add(order);
         msg += "Buy ";
      }
      
      msg += String.format("%s (%s)\n%i shares at ", symbol, name, order.getShares());
      if (order.isMarket()) {
         msg += "market";
      } else {
         msg += "$" + order.getPrice();
      }
      
      order.getTrader().receiveMessage(msg); // Send trader confirmation the order has been placed
      
   }
}