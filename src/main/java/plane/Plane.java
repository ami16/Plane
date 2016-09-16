package plane;

import java.util.Scanner;

public class Plane implements Flyable {

   private double planeWeight ;
   private double totalFirePotential;
   private int flightRange;
   private int flightHeight;
   private Ammo ammo ;
   private boolean inAction = false ;
   private boolean planeSaved = true ;

   public Plane() { }

   public double  getPlaneWeight() { return planeWeight; }
   public double  getTotalFirePotential() { return totalFirePotential; }
   public int     getFlightHeight() { return flightHeight; }
   public int     getFlightRange() { return flightRange; }

   public void    setFlightRange(int flightRange) { this.flightRange = flightRange; }
   public void    setPlaneSaved(boolean planeSaved) { this.planeSaved = planeSaved; }


   public void fly(){

      PlaneFunc func = new PlaneFunc() ;
      Scanner scan = new Scanner(System.in) ;

      if( !inAction )
         ammo = new Ammo() ;

      System.out.println("\n>-->    Plane has took off    >-->\n");

      // DIRECTION
      System.out.println("Choose direction (N - north, S - south, E - east, W - west):");
      char direction = func.getDirection(scan) ;

      // Search 4 TARGET?
      System.out.println("Search for target? (y/n) ");
      String searchForTarget = func.checkYN(scan).trim() ;


      if( searchForTarget.equalsIgnoreCase("y") ){

         while (true) {

            System.out.println("Found targets on this direction (1, 2 or 3) (random yet): ");
            char target = func.chooseTarget(scan, direction);

            /* ----------------------------
            *  Make some random target yet
            * -----------------------------*/
            // >---- TARGET ----<
            Target tgt = new Target(target) ;

            // <---- PLANE ---->

            planeWeight = func.getPlaneWeight("", this.ammo);
            totalFirePotential = (ammo.getRocketsCount() * Constants.ROCKET_KOF) + ammo.getBulletsCount();
            flightHeight = func.getFlightParams(planeWeight)[0];

            if( !inAction )
               flightRange = func.getFlightParams(planeWeight)[1];


            // Attack?
            if (func.checkFightResources(this, tgt)) {

               // Operation Description
               func.getOperationDescription(this, ammo, tgt);

               System.out.println("Plain's ready to strike. Attack? (y/n)");
               // Y/N
               String attackReply = func.checkYN(scan).trim();

               // YES, ATTACK!
               if (attackReply.equalsIgnoreCase("y") ) {
                  // Operation Process
                  boolean process = func.processOperation(this, ammo, tgt);
                  if( !process )
                     break ;

                  inAction = true ;

                  // ???
                  // Search for more targets?
                  System.out.println("Search for more targets? (y/n)");
                  // Y/N
                  char searchMoreReply = func.checkYN2(scan);
                  if( searchMoreReply == 'y' ){

                     // DIRECTION
                     System.out.println("Choose new direction (N - north, S - south, E - east, W - west):");
                     direction = func.getDirection(scan) ;

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

               func.getOperationDescription(this, ammo, tgt);

               if( planeSaved ){
                  func.sayReturning();
               } else {
                  System.out.println("<<We've lost the Plane...>>");
               }
               break;
            }
         }
         // while
      }
      else if( planeSaved ) System.out.println("<<Plane returned to homeplace.>>"); else System.out.println("<<We've lost the Plane...>>");
   }
   // fly
}
// class