public class BitsAndPieces {
   private NewString name;
   private Integer number;
   
   /* this method takes in a length and returns a random string made up of characters */
   
   public  static String getString(int length) {
      String result = "";
      for (int i = 0; i < length; i++) {
         result += "" + (char) ((int)(Math.random()*26)+42);
      }
      return result;
   }
   
   BitsAndPieces() {
      name = new NewString(getString((int)(Math.random()*4+3)));
      number = (int)(Math.random()*500+250);
   }
   
   public NewString getName() {
      return name;
   }
     
   public Integer getNumber() {
      return number;
   }
      
   public void setName(String str) {
      name.setString(str);
   }
     
   public void setNumber(Integer number) {
      this.number = number;
   }
   public String toString() { 
      return name.getString() + "(" + number + ")";
   }
 
}
