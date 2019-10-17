import java.util.Comparator;
public class PriceComparator implements Comparator<TradeOrder> {

   private boolean ascending;

   public PriceComparator() {
      ascending = true;
   }
   
   public PriceComparator(boolean asc) {
      ascending = asc;
   }
   
   public int compare(TradeOrder order1, TradeOrder order2) {
      if (order1.isMarket() && order2.isMarket())
         return 0;
      else if (order1.isMarket() && order2.isLimit())
         return -1;
      else if (order1.isLimit() && order2.isMarket())
         return 1;
      else if (order1.isLimit() && order2.isLimit()) {
         int cents1 = (int)Math.round(order1.getPrice() * 100);
         int cents2 = (int)Math.round(order2.getPrice() * 100);
         
         if (ascending) {
            return cents1 - cents2;
         } else {
            return cents2 - cents1;
         }
      }
      else
    	  return -2;
   }
}