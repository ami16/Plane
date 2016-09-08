package plane;

import java.util.Random;

import static plane.Constants.MAX_ROCKETS ;
import static plane.Constants.MIN_ROCKETS ;
import static plane.Constants.MAX_BULLETS ;
import static plane.Constants.MIN_BULLETS ;


public class Ammo {

   private double rocketsCount ;
   private double bulletsCount ;

   public Ammo() {
      Random rnd = new Random() ;
      this.rocketsCount = rnd.nextInt ((MAX_ROCKETS - MIN_ROCKETS) + 1) + MIN_ROCKETS;
      this.bulletsCount = rnd.nextInt ((MAX_BULLETS - MIN_BULLETS) + 1) + MIN_BULLETS;
   }

   public double getRocketsCount() {
      return rocketsCount;
   }

   public double getBulletsCount() {
      return bulletsCount;
   }
}