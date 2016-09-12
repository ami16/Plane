package plane;

//import java.io.IOException;

public class App {


//   public static void main(String[] args) throws IOException, InterruptedException {
   public static void main(String[] args) {
      // windows only
//      Process p = Runtime.getRuntime().exec("cmd c://java start cmd.exe");
//      p.waitFor();

      Plane pl = new Plane() ;
      pl.fly();
   }
}