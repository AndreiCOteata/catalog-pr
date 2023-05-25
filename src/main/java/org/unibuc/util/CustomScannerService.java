package org.unibuc.util;

import java.util.Scanner;

public class CustomScannerService {
    private static final Scanner scanner = new Scanner(System.in);

    public static Long getLongValue(){
        while(true){
            if(scanner.hasNextLong()) {
                long value = scanner.nextLong();
                scanner.nextLine();
                return value;
            }
            System.out.println("Please insert a numeric value");
        }
    }

    public static Integer getIntegerValue(){
        while(true){
            if(scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            System.out.println("Please insert a numeric value");
        }
    }

    public static String getStringValue(){
        return scanner.nextLine();
    }
}
