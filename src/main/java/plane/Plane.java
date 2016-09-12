package plane;

import java.util.Scanner;

public class Plane implements Flyable {

   public double totalFirePotential;
   public int flightRange;

   public Plane() { }


   public void fly(){

      PlaneFunc func = new PlaneFunc() ;
      Scanner scan = new Scanner(System.in) ;

      Ammo ammo = new Ammo() ;


      // DIRECTION
      System.out.println("\n>-->    Plane has took off    >-->\n");

      System.out.println("Choose direction (N - north, S - south, E - east, W - west):");
      char direction = func.getDirection(scan) ;

      // TARGET
      System.out.println("Chose your target on this direction (1, 2 or 3): ");
      char target = func.getTarget(scan, direction) ;


      /* ----------------------------
      *  Make some random target yet
      * -----------------------------*/
      // target
      Target target1 = new Target(target) ;
      int targetDistance = target1.getDistance() ,
          targetArmor = target1.getArmor() ;

      // plane
      double rndPlaneWeight = func.getPlaneWeight("");
      this.totalFirePotential = ( ammo.getRocketsCount() * Constants.ROCKET_KOF ) + ammo.getBulletsCount() ;
      int flightHight = func.getFlightParams(rndPlaneWeight)[0] ;
      this.flightRange = func.getFlightParams(rndPlaneWeight)[1] ;

      // ???
      // Attack?
      if ( (targetDistance <= this.flightRange) && (targetArmor <= this.totalFirePotential) ){
         // Operation Description
         func.getOperationDescription( targetDistance, targetArmor, ammo, this.totalFirePotential, rndPlaneWeight, flightHight, this.flightRange );

         System.out.println("We're ready to strike. Attack? (y/n)");
         // Y/N
         char attackReply = func.checkYN(scan) ;

         // YES, ATTACK!
         if( attackReply == 'y' ){
            // Operation Process
            func.processOperation(totalFirePotential, targetArmor, flightRange, targetDistance);
            this.totalFirePotential -= targetArmor ;
            this.flightRange -= targetDistance ;

            // ???
            // Search for more targets?
            System.out.println("Search for more targets? (y/n)");
            // Y/N
            char searchMoreReply = func.checkYN(scan);


            System.out.println( searchMoreReply + ". exit");

            // CHECK distance again
//            if(){
//
//            }

         } else {
            func.sayBye();
         }

      } else {
         System.out.println("Not enough fight resources: ");
         func.getOperationDescription( targetDistance, targetArmor, ammo, this.totalFirePotential, rndPlaneWeight, flightHight, this.flightRange );
      }


   }

}