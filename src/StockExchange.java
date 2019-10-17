import java.util.HashMap;
public class StockExchange {
   private HashMap<String, Stock> stocks;
   
   public StockExchange() {
      stocks = new HashMap<String, Stock>();
   }
   
   public void listStock(String symbol, String name, double price) {
      stocks.put(symbol, new Stock(symbol, name, price));
   }
   
   public String getQuote(String symbol) {
      return stocks.get(symbol).getQuote();
   }
   
   public void placeOrder(TradeOrder order) {
      stocks.get(order.getSymbol()).placeOrder(order);
   }
}