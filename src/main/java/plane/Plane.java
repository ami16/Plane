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
   private int homeDistance = 0 ;
   private int axisN ;
   private int axisE ;
   private int axisS ;
   private int axisW ;

   public Plane() { }

   public double  getTotalFirePotential() { return totalFirePotential; }
   public int     getFlightRange() { return flightRange; }
   public void    setFlightRange(int flightRange) { this.flightRange = flightRange; }
   public int     getHomeDistance() { return homeDistance; }
   public void    setHomeDistance(int homeDistance) { this.homeDistance = homeDistance; }

   public int getAxisN() { return axisN; }
   public int getAxisE() { return axisE; }
   public int getAxisS() { return axisS; }
   public int getAxisW() { return axisW; }

   public void setAxisN(int axisN) { this.axisN = axisN; }
   public void setAxisE(int axisE) { this.axisE = axisE; }
   public void setAxisS(int axisS) { this.axisS = axisS; }
   public void setAxisW(int axisW) { this.axisW = axisW; }


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

               // Y/N
               System.out.println("Plain's ready for attack. Proceed? (y/n)");
               String attackReply = func.checkYN(scan).trim();

               // YES, ATTACK!
               if (attackReply.equalsIgnoreCase("y") ) {
                  // Operation Process
                  func.processOperation(this.ammo, this.totalFirePotential, targetArmor, this.flightRange, targetDistance, this);
                  func.setHomeDistance( this.direction, targetDistance, this );
                  System.out.println("CALLED FROM PLANE homeDistance: " + this.getHomeDistance() );

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