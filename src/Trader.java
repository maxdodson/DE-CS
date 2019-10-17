import java.util.Queue;
import java.util.Iterator;
public class Trader {
   private Queue<String> mailbox;
   private String name;
   private String password;
   private Brokerage brokerage;
   private TraderWindow myWindow;
   
      
   public Trader(Brokerage brokerage, String name, String password) {
      this.brokerage = brokerage;
      this.name = name;
      this.password = password;
   }
     
   public int compareTo(Trader other) {
      return name.toLowerCase().compareTo(other.getName().toLowerCase());
   }
   
   public boolean equals(Object other) {
      if (other != null && other instanceof Trader) {
			return compareTo((Trader)other) == 0;
		}
		else {
			throw new ClassCastException();
		}

   }
   
   public String getName() {
      return name;
   }
   
   public String getPassword() {
      return password;
   }
   
   public boolean hasMessages() {
      return !mailbox.isEmpty();
   }
   
   public void receiveMessage(String msg) { // Check if user must be logged in to remove messages
      mailbox.add(msg);
      if (myWindow != null) {
         readMailbox();
      }
   }
   
   public void readMailbox() {
      Iterator<String> iter = mailbox.iterator();
      while (iter.hasNext()) {
         myWindow.showMessage(iter.next());
         iter.remove();
      }
   }
   
   public void openWindow() {
      myWindow = new TraderWindow(this);
      readMailbox();
   }
   
   public void getQuote(String symbol) {
      brokerage.getQuote(symbol, this);
   }
   
   public void placeOrder(TradeOrder order) {
      brokerage.placeOrder(order);
   }
   
   public void quit() {
      brokerage.logout(this);
      myWindow = null;
   }
     
}