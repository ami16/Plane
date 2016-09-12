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
      System.out.println("Plane has took off");

      System.out.println("Choose direction (N - north, S - south, E - east, W - west):");
      char direction = 0 ;
      boolean isDirection = false ;
      do {
         direction = scan.next().charAt(0) ;
         if( func.isDirection(direction) ){
            isDirection = true ;
         } else {
            System.out.println("Choose correct direction: ");
         }
      } while (!isDirection) ;


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
      double rndPlaneWeight = func.getPlaneWeight("");
      this.totalFirePotential = ( ammo.getRocketsCount() * Constants.ROCKET_KOF ) + ammo.getBulletsCount() ;
      int flightHight = func.getFlightParams(rndPlaneWeight)[0] ;
      this.flightRange = func.getFlightParams(rndPlaneWeight)[1] ;

      // Attack?
      if ( (targetDistance <= this.flightRange) && (targetArmor <= this.totalFirePotential) ){
         // Operation Description
         func.getOperationDescription( targetDistance, targetArmor, ammo, this.totalFirePotential, rndPlaneWeight, flightHight, this.flightRange );
         System.out.println("We're ready to strike. Attack? (y/n)");

         String attackReply ;
         boolean isAttackReply;
         do{
            attackReply = scan.nextLine() ;
            if(attackReply.equalsIgnoreCase("y") || attackReply.equalsIgnoreCase("n")){
               isAttackReply = true ;
            } else {
               System.out.println("Wrong choice. Y or N: ");
               isAttackReply = false ;
            }
         } while (!isAttackReply);

         // YES, ATTACK!
         if( attackReply.equalsIgnoreCase("y") ){
            // Operation Process
            func.processOperation(totalFirePotential, targetArmor, flightRange, targetDistance);
            this.totalFirePotential -= targetArmor ;
            this.flightRange -= targetDistance ;



         } else {
            func.sayBye();
         }

      }


   }

}