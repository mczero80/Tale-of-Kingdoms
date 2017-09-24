package mods.aginsun.kingdoms.handlers;

import java.util.Random;

public class GoldKeeper {

   public static int goldTotal;
   public static int bankGold;
   public static int hunter = 0;
   public static float flint;
   public static float clay;
   public static float iron;
   public static float diamond;
   public static float fish;
   public static float apple;
   public static float string;
   public static float feather;


   public static int getGoldTotal() {
      return goldTotal;
   }

   public static void addGold(int i) {
      goldTotal += i;
   }

   public static void setGoldTotal(int i) {
      goldTotal = i;
   }

   public static void decreaseGold(int i) {
      goldTotal -= i;
   }

   public static int getBankGold() {
      return bankGold;
   }

   public static void addBankGold(int i) {
      bankGold += i;
   }

   public static void setBankGold(int i) {
      bankGold = i;
   }

   public static void decreaseBankGold(int i) {
      bankGold -= i;
   }

   public static int priceItem(String s)
   {
      return 0;
   }

   public static void getRandomStock() {
      Random random = new Random();

      int i;
      for(i = 0; i <= 3; i = random.nextInt(200)) {
         ;
      }

      flint = (float)i;

      for(i = 0; i <= 3; i = random.nextInt(200)) {
         ;
      }

      clay = (float)i;

      for(i = 0; i <= 3; i = random.nextInt(200)) {
         ;
      }

      iron = (float)i;

      for(i = 0; i <= 3; i = random.nextInt(200)) {
         ;
      }

      diamond = (float)i;

      for(i = 0; i <= 3; i = random.nextInt(200)) {
         ;
      }

      fish = (float)i;

      for(i = 0; i <= 3; i = random.nextInt(200)) {
         ;
      }

      apple = (float)i;

      for(i = 0; i <= 3; i = random.nextInt(200)) {
         ;
      }

      string = (float)i;

      for(i = 0; i <= 3; i = random.nextInt(200)) {
         ;
      }

      feather = (float)i;
   }

}
