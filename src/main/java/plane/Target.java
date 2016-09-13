package plane;

import java.util.Random;

import static plane.Constants.MIN_TARGET_ARMOR;
import static plane.Constants.MAX_TARGET_ARMOR;
import static plane.Constants.MIN_TARGET_DISTANCE;
import static plane.Constants.MAX_TARGET_DISTANCE;


public class Target {

   private int distance;      // 300-1500
   private int armor;         // 260-470-680

   public Target(char loc) {
      Random rnd = new Random() ;

      distance = rnd.nextInt((MAX_TARGET_DISTANCE - MIN_TARGET_DISTANCE)+1) + MIN_TARGET_DISTANCE;
      armor = rnd.nextInt((MAX_TARGET_ARMOR - MIN_TARGET_ARMOR)+1) + MIN_TARGET_ARMOR;
//      armor = 50 ;
   }

   public int getDistance() {
      return distance;
   }

   public int getArmor() {
      return armor;
   }

}