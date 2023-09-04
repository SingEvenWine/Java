package Commonly_Used_Classes.Question3;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Calendar {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2023, 3, 1);
        System.out.println("      March 2023      ");
        System.out.println("Mo Tu We Th Fr Sa Su");
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int value = dayOfWeek.getValue();
        for (int i = 1; i < value; i++) {
            System.out.print("   ");
        }
        for (int i = 1; i <= localDate.lengthOfMonth(); i++) {
            System.out.printf("%2d ", i);

            if ((i + value - 1) % 7 == 0) {
                System.out.println();
            }
        }
    }
}