package plane;

import java.util.Scanner;

public class Plane implements Flyable {

   private double planeWeight ;
   private double totalFirePotential;
   private int flightRange;
   private int flightHeight;
   private char direction ;
   private boolean inAction = false ;
   private Ammo ammo ;

   public Plane() { }

   public double  getTotalFirePotential() { return totalFirePotential; }
   public int     getFlightRange() { return flightRange; }
   public int     getFlightHeight() { return flightHeight; }
   public char    getDirection() { return direction; }
   public double  getPlaneWeight() { return planeWeight; }

   public void    setAmmo(Ammo ammo) { this.ammo = ammo; }


   public void fly(){

      PlaneFunc func = new PlaneFunc() ;
      Scanner scan = new Scanner(System.in) ;

      if( !this.inAction )
         ammo = new Ammo() ;

      System.out.println("\n>-->    Plane has took off    >-->\n");

      // DIRECTION
      System.out.println("Choose direction (N - north, S - south, E - east, W - west):");
      this.direction = func.getDirection(scan) ;


      // Search 4 TARGET?
      System.out.println("Search for target? (y/n) ");
      char searchForTarget = func.checkYN(scan) ;

      if( searchForTarget == 'y' ){


         while (true) {

            System.out.println("Found targets on this direction (1, 2 or 3) (random yet): ");
            char target = func.chooseTarget(scan, this.direction);

            /* ----------------------------
            *  Make some random target yet
            * -----------------------------*/
            // >---- TARGET ----<
            Target tgt = new Target(target) ;
            int targetDistance = tgt.getDistance(),
                targetArmor = tgt.getArmor();

            // <---- PLANE ---->
            this.planeWeight = func.getPlaneWeight("");
            this.totalFirePotential = (ammo.getRocketsCount() * Constants.ROCKET_KOF) + ammo.getBulletsCount();
//            if( !this.inAction ){
//               this.planeWeight = func.getPlaneWeight("");
//               this.totalFirePotential = (ammo.getRocketsCount() * Constants.ROCKET_KOF) + ammo.getBulletsCount();
//            } else {
//               this.planeWeight = Math.round(this.planeWeight * 0.97);
//            }
            this.flightHeight = func.getFlightParams(this.planeWeight)[0];
            this.flightRange = func.getFlightParams(this.planeWeight)[1];


            // ???
            // Attack?
            if (func.checkFightResources(this, tgt)) {

               // Operation Description
               func.getOperationDescription(targetDistance, targetArmor, ammo, this.totalFirePotential, this.planeWeight, this.flightHeight, this.flightRange);

               System.out.println("Plain's ready to strike. Attack? (y/n)");
               // Y/N
               char attackReply = func.checkYN(scan);

               // YES, ATTACK!
               if (attackReply == 'y') {
                  // Operation Process
                  func.processOperation(ammo, this.totalFirePotential, targetArmor, this.flightRange, targetDistance);

                  this.inAction = true ;
                  this.totalFirePotential -= targetArmor;
                  this.flightRange -= targetDistance;

                  // ???
                  // Search for more targets?
                  System.out.println("Search for more targets? (y/n)");
                  // Y/N
                  char searchMoreReply = func.checkYN(scan);
                  if( searchMoreReply == 'y' ){
                     // DIRECTION
                     System.out.println("Choose new direction (N - north, S - south, E - east, W - west):");
                     this.direction = func.getDirection(scan) ;

                     continue;
                  } else {
                     func.sayBye();
                     break;
                  }

               } else {
                  func.sayBye();
                  break;
               }

            } else {
               System.out.println("-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-");
               System.out.println("        NOT ENOUGH FIGHT RESOURCES          ");
               System.out.println("-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-");
               func.getOperationDescription(targetDistance, targetArmor, ammo, this.totalFirePotential, this.planeWeight, this.flightHeight, this.flightRange);
               func.sayReturning();
               break;
            }

         }
         // while

      }
      else {
         System.out.println("Plane returned to homeplace.");
      }













   }

}