package Utils;

public class DateTimeUtils {


    public static void wait(int timeout){
        try{
            Thread.sleep(timeout *1000L); //pored 1000 je L jer je long a thread prihvata samo milisekunde
        } catch (InterruptedException e) {

            System.out.println("Exception in Thread.sleep()! Message:" + e.getMessage());
        }
    }
}
