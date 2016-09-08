package plane;

import java.util.Scanner;

public class Plane implements Flyable {

   public Plane() { }


   public void fly(){

      PlaneFunc func = new PlaneFunc() ;
      Scanner scan = new Scanner(System.in) ;

      Ammo ammo = new Ammo() ;


      // DIRECTION
      System.out.println("Choose direction (N - north, S - south, E - east, W - west):");
      char direction = 0 ;
      boolean isDirection = false ;
      do {
         direction = scan.next().charAt(0) ;
         if( func.isDirection(direction) ){
            isDirection = true ;
         } else {
            System.out.println("Input correct direction: ");
         }
      } while (!isDirection) ;
      System.out.print(direction);


      // TARGET
      System.out.println("Chose your target on this direction (1, 2 or 3): ");
      char target = 0 ;
      boolean isTarget = false ;
      do {
         target = scan.next().charAt(0) ;
         if( func.isTarget(target) ){
            isTarget = true ;
         } else {
            System.out.println("Make correct choice (1, 2 or 3): ");
         }
      } while ( !isTarget ) ;


      /* ----------------------------
      *  Make some random target yet
      * -----------------------------*/
      // target
      Target target1 = new Target(target) ;
      int targetDistance = target1.getDistance() ,
          targetArmor = target1.getArmor() ;

      // plane
      double rndPlaneWeight = func.getPlaneWeight("") ,
          totalFirePotential = ( ammo.getRocketsCount() * Constants.ROCKET_KOF ) + ammo.getBulletsCount() ;
      int flightHight = func.getFlightParams(rndPlaneWeight)[0] ,
          flightRange = func.getFlightParams(rndPlaneWeight)[1] ;


      func.getOperationDescription( targetDistance, targetArmor, ammo, totalFirePotential, rndPlaneWeight, flightHight, flightRange ); ;

      func.processOperation(totalFirePotential, targetArmor, flightRange, targetDistance);

   }

}