import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class test {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.now();
        String timer = 15 + ":" + 21;
        String timeNow = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println(timeNow);
        System.out.println(timeNow.split(":")[0]);
        int i = Integer.parseInt(timeNow.split(":")[0]);
        System.out.println(i);
    }

}
