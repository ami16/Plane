package plane;

import java.util.Random;
import java.util.Scanner;

import static plane.Constants.PLANE_WEIGHT ;
import static plane.Constants.MIN_STEALTH_TIME ;
import static plane.Constants.MAX_STEALTH_TIME ;
import static plane.Constants.PILOT_WEIGHT ;
import static plane.Constants.ENGINE_WEIGHT ;
import static plane.Constants.CHASSIS_WEIGHT ;
import static plane.Constants.CAB_WEIGHT ;
import static plane.Constants.BULLET_WEIGHT ;
import static plane.Constants.MAX_ROCKETS ;
import static plane.Constants.MIN_ROCKETS ;
import static plane.Constants.MAX_BULLETS ;
import static plane.Constants.MIN_BULLETS ;
import static plane.Constants.ROCKET_WEIGHT ;
import static plane.Constants.ROCKET_KOF ;
import static plane.Constants.SHOTGUN_WEIGHT ;




public class PlaneFunc {

   public boolean isTarget(char tar){
      boolean is = false ;
      switch (tar){
         case '1' :case '2' :case '3':
            is = true ; break;
      }
      return is;
   }


   // Not created yet. Random used instead
   public String getTargetsFromDirection(char dir){
      // N
      // S
      // E
      // W
      switch( Character.toLowerCase(dir) ){ case 'n': }
      return "" ;
   }


   public double getPlaneWeight(String min_max){

      double totalWeight = 0 ;

      Ammo ammo = new Ammo() ;

      if(min_max.equalsIgnoreCase("max")){
         // max (only for testing purposes)
         totalWeight =  PLANE_WEIGHT + CAB_WEIGHT + PILOT_WEIGHT + CHASSIS_WEIGHT + ENGINE_WEIGHT + SHOTGUN_WEIGHT +
             (ROCKET_WEIGHT * MAX_ROCKETS) + (BULLET_WEIGHT * MAX_BULLETS) ;
      } else if(min_max.equalsIgnoreCase("min")){
         // min (only for testing purposes)
         totalWeight =  PLANE_WEIGHT + CAB_WEIGHT + PILOT_WEIGHT + CHASSIS_WEIGHT + ENGINE_WEIGHT + SHOTGUN_WEIGHT +
             (ROCKET_WEIGHT * MIN_ROCKETS) + (BULLET_WEIGHT * MIN_BULLETS) ;
      } else {
         // random
         totalWeight =  PLANE_WEIGHT + CAB_WEIGHT + PILOT_WEIGHT + CHASSIS_WEIGHT + ENGINE_WEIGHT + SHOTGUN_WEIGHT +
             (ROCKET_WEIGHT * ammo.getRocketsCount()) + (BULLET_WEIGHT * ammo.getBulletsCount()) ;
      }
      return totalWeight ;
   }


   public int[] getFlightParams( double totalWeight ){

      int[] arr = new int[2] ;
//      int plusKm = 1000 ;
      int plusKm = 8000 ; // TEST. To make Stealth appear faster
      Random rnd = new Random() ;

      if(totalWeight <= 11993){
         arr[0] = rnd.nextInt((4000-3360)+1)+3360 ;            // height  : 4000 - 3360 (h)
         arr[1] = rnd.nextInt((1500-1260)+1)+1260 + plusKm ;   // range : 1500 - 1260
      } else if(totalWeight <= 12126){
         arr[0] = rnd.nextInt((3360-2720)+1)+2720 ;            //height : 3360 - 2720 (h)
         arr[1] = rnd.nextInt((1260-1020)+1)+1020  + plusKm;   // range : 1260 - 1020
      } else if(totalWeight <= 12259){
         arr[0] = rnd.nextInt((2720-2080)+1)+2080 ;            // height : 2720 - 2080 (h)
         arr[1] = rnd.nextInt((1020-780)+1)+780  + plusKm;     // range : 1020 - 780
      } else if(totalWeight <= 12392){
         arr[0] = rnd.nextInt((2080-1440)+1)+1440 ;            // height : 2080 - 1440 (h)
         arr[1] = rnd.nextInt((780-540)+1)+540 + plusKm ;      // range : 780 - 540
      } else if(totalWeight <= 12650){
         arr[0] = rnd.nextInt((1440-800)+1)+800 ;              // height : 1440 - 800 (h)
         arr[1] = rnd.nextInt((540-300)+1)+300  + plusKm;      // range : 540 - 300
      }
      return arr ;
   }


   public void getOperationDescription( int targetDistance, int targetArmor, Ammo ammo, double totalFirePotential, double rndPlaneWeight, int flightHight, int flightRange ){
      System.out.print("Target: \nDistance (300-1500): " + targetDistance + " km, ");
      System.out.println("Armor (260-680): " + targetArmor + " FP \n-------------------------------------------------------------");

      String out = "rockets: " + ammo.getRocketsCount() + " ( = " + ammo.getRocketsCount() * ROCKET_KOF + " blts), " +
          "bullets: " + ammo.getBulletsCount()  + ", \nTOTAL FIRE potential: " + totalFirePotential + " FP";
      System.out.println("PLANE: \nWEAPONS: " + out);
      System.out.println( "Weight: " + rndPlaneWeight + " kg, " );
      System.out.println( "FLIGHT params: Height = " + flightHight + " m, flight range = " + flightRange + " km\n" );

   }


   public void processOperation(double totalFirePotential, int targetArmor, int flightRange, int targetDistance){
      if( totalFirePotential < targetArmor  ){
         System.out.print("Target is too strong! ");
         if( flightRange > targetDistance*2 ){
            System.out.print("Turning back. ");
         } else {
            System.out.println("And there would be no way to turn back. Quit operation.");
         }
      } else if( totalFirePotential > targetArmor ){
         if( flightRange > targetDistance && flightRange < targetDistance*2){
            System.out.println("TARGET DESTROYED! But plane had NOT came back... Lacked " + (targetDistance*2-flightRange) + " km" );
         } else if( flightRange > targetDistance*2 ){
            this.stealth(MIN_STEALTH_TIME, MAX_STEALTH_TIME);
//            System.out.println("TARGET DESTROYED! And Plane is SAVED back home.");
            System.out.println("TARGET DESTROYED!");
         } else {
            System.out.println("Target could be destroyed, but plane's lacking of flight resources... Quit operation.");
         }
      }
   }


   public char checkYN( Scanner scan ){
      char reply ;
      boolean is = false;
      do{
         reply = scan.next().charAt(0) ;
         if( Character.toLowerCase(reply) == 'y' || Character.toLowerCase(reply) == 'n' ){
            is = true ;
         } else {
            System.out.println("Wrong choice. Might be Y or N: ");
         }
      } while (!is);
      return reply ;
   }


   public char getDirection( Scanner scan ){
      char direction = 0 ;
      boolean is = false ;
      do {
         direction = scan.next().charAt(0) ;
         switch ( Character.toLowerCase(direction) ){
            case 'n' :case 's' :case 'e':case 'w':
               is = true ;
               break;
         }
         System.out.println("Choose correct direction: ");
      } while ( !is ) ;
      return direction ;
   }


   public char getTarget( Scanner scan, char direction ){
      char target = 0 ;
      boolean isTarget = false ;
      do {
         target = scan.next().charAt(0) ;
         if( this.isTarget(target) ){
            isTarget = true ;
         } else {
            System.out.println("Make correct choice (1, 2 or 3): ");
         }
      } while ( !isTarget ) ;
      return target ;
   }


   public boolean checkFightResources( Plane pl, Target target ){
      return (target.getDistance() <= pl.getFlightRange()) && (target.getArmor() <= pl.getTotalFirePotential()) ;
   }


   public void stealth(int min, int max){
      Random rnd = new Random() ;

      System.out.println("\n>-->-->-->-->-->-->-->-->-->-->-->-->-->-->-->-->-->-->\nActivating Stealth function...");
      long start_time = System.currentTimeMillis();
      try{
         Thread.sleep( rnd.nextInt((max - min) + 1) + min );
      } catch (Exception e){
         System.out.println("Got sleep exception");
      }
      long end_time = System.currentTimeMillis();

      System.out.println( "Stealth was active for: " + ( (double)(end_time - start_time)/1000 ) + " sec. (1.5 - 3.4) \n>-->-->-->-->-->-->-->-->-->-->-->-->-->-->-->-->-->--> \n" );
   }


   public void sayBye(){
      System.out.println("Ok, see ya.");
   }
}