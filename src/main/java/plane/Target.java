package plane;

import java.util.Random;

import static plane.Constants.TARGET_MIN_ARMOR ;
import static plane.Constants.TARGET_MAX_ARMOR ;
import static plane.Constants.TARGET_MIN_DISTANCE ;
import static plane.Constants.TARGET_MAX_DISTANCE ;


public class Target {

   private int distance;    // 300-1500
   private int armor;          // 260-470-680

   public Target(char loc) {
      Random rnd = new Random() ;

      distance = rnd.nextInt((TARGET_MAX_DISTANCE - TARGET_MIN_DISTANCE)+1) + TARGET_MIN_DISTANCE ;
      armor = rnd.nextInt((TARGET_MAX_ARMOR - TARGET_MIN_ARMOR)+1) + TARGET_MIN_ARMOR ;
   }

   public int getDistance() {
      return distance;
   }

   public int getArmor() {
      return armor;
   }
}