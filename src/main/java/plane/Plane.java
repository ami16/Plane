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
   public void    setFlightRange(int flightRange) { this.flightRange = flightRange; }

//   public int     getFlightHeight() { return flightHeight; }
//   public char    getDirection() { return direction; }
//   public double  getPlaneWeight() { return planeWeight; }
//   public void    setAmmoBullets(int bullets) { this.ammo.setBulletsCount(bullets); }
//   public void    setAmmoRockets(int rockets) { this.ammo.setRocketsCount(rockets); }


   public void fly(){

      PlaneFunc func = new PlaneFunc() ;
      Scanner scan = new Scanner(System.in) ;

      if( !this.inAction )
         this.ammo = new Ammo() ;

      System.out.println("\n>-->    Plane has took off    >-->\n");

      // DIRECTION
      System.out.println("Choose direction (N - north, S - south, E - east, W - west):");
      this.direction = func.getDirection(scan) ;


      // Search 4 TARGET?
      System.out.println("Search for target? (y/n) ");
      String searchForTarget = func.checkYN(scan).trim() ;


      if( searchForTarget.equalsIgnoreCase("y") ){


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

            this.planeWeight = func.getPlaneWeight("", this.ammo);

            this.totalFirePotential = (this.ammo.getRocketsCount() * Constants.ROCKET_KOF) + this.ammo.getBulletsCount();
            this.flightHeight = func.getFlightParams(this.planeWeight)[0];
            if( !this.inAction )
               this.flightRange = func.getFlightParams(this.planeWeight)[1];


            // ???
            // Attack?
            if (func.checkFightResources(this, tgt)) {

               // Operation Description
               func.getOperationDescription(targetDistance, targetArmor, this.ammo, this.totalFirePotential, this.planeWeight, this.flightHeight, this.flightRange);

               System.out.println("Plain's ready to strike. Attack? (y/n)");
               // Y/N
               String attackReply = func.checkYN(scan).trim();

               // YES, ATTACK!
               if (attackReply.equalsIgnoreCase("y") ) {
                  // Operation Process
                  func.processOperation(this.ammo, this.totalFirePotential, targetArmor, this.flightRange, targetDistance, this);

                  this.inAction = true ;

                  // ???
                  // Search for more targets?
                  System.out.println("Search for more targets? (y/n)");
                  // Y/N
                  char searchMoreReply = func.checkYN2(scan);
                  if( searchMoreReply == 'y' ){

                     // DIRECTION
                     System.out.println("Choose new direction (N - north, S - south, E - east, W - west):");
                     this.direction = func.getDirection(scan) ;

                  } else {
                     func.sayBye();
                     break;
                  }

               } else {
                  func.sayBye();
                  break;
               }

            } else {
               System.out.println("-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•");
               System.out.println("              INSUFFICIENT FIGHT RESOURCES                ");
               System.out.println("-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•");
               func.getOperationDescription(targetDistance, targetArmor, this.ammo, this.totalFirePotential, this.planeWeight, this.flightHeight, this.flightRange);
               func.sayReturning();
               break;
            }

         }
         // while

      }
      else { System.out.println("Plane returned to homeplace."); }

   }
   // fly
}
// class