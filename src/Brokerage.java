import java.util.TreeSet;
import java.util.TreeMap;
public class Brokerage implements Login {
   TreeMap<String, Trader> traders;
   TreeSet<Trader> activeTraders;
   StockExchange exchange;
   
   public Brokerage(StockExchange exchange) {
      traders = new TreeMap<String, Trader>();
      activeTraders = new TreeSet<Trader>();
      this.exchange = exchange;
   }
   
   public int addUser(String name, String password) {
      if (!(name.length() >= 4 && name.length() <= 10))
         return -1;
      if (!(password.length() >= 2 && password.length() <= 10))
         return -2;
      if (traders.get(name) != null)
         return -3;
      traders.put(name, new Trader(this, name, password));
      return 0;
   }
   
   public int login(String name, String password) {
      if (activeTraders.contains(new Trader(this, name, password))) // User already logged in
         return -3;
      Trader trader = traders.get(name); // If user not logged in, attempt to find in registered traders
      if (trader != null) { // User found in list of registered traders
         if (password.equals(trader.getPassword())) { // Verify user's password
            activeTraders.add(trader); // Login user
            if (!trader.hasMessages()) {
               trader.receiveMessage("Welcome to SafeTrade!"); // Send user welcome message if mailbox is empty
            }
            trader.openWindow();
         } else {
            return -2; // Invalid password
         }
      }
      else {
         return -1; // User not found in list of registered traders
      }
      return -1;
   }
   
   public void logout(Trader trader) {
      activeTraders.remove(trader); // Remove trader from set of logged-in traders
   }
   
   public void getQuote(String symbol, Trader trader) {
      trader.receiveMessage(exchange.getQuote(symbol));
   }
   
   public void placeOrder(TradeOrder order) {
      exchange.placeOrder(order);
   }
}