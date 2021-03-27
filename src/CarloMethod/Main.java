package CarloMethod;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws InterruptedException {
        int threadNumber = 3;
        System.out.println("This program count PI number with CARLO METHOD, in single thread and multiple thread to compare them.");
        System.out.print("So please enter a radius : ");
        Scanner scanner = new Scanner(System.in);
        int radius = scanner.nextInt();

        System.out.println();

        singleThread(radius);
        multipleThread(radius, threadNumber);
    }

    static private void singleThread (int radius){
        long startTime = System.nanoTime();

        System.out.println("Single Thread : ");

        CountPINumber countPINumber = new CountPINumber(radius, 10000000);
        countPINumber.startSingleThread();
        System.out.println("PI :  " + countPINumber.getPI());

        long endTime = System.nanoTime();
        System.out.print("Time : ");
        System.out.print((endTime - startTime) / 1000000.0);
        System.out.println(" ms");
        System.out.println();
    }

    static private void multipleThread (int radius, int threadNumber) throws InterruptedException {
        long startTime = System.nanoTime();

        System.out.println("Multiple Thread : ");

        CountPINumber countPINumber = new CountPINumber(radius, 10000000);
        ArrayList<Thread> threads = new ArrayList<>();

        for (int counter = 0 ; counter < threadNumber ; counter++){
            threads.add(new Thread(countPINumber));
        }

        for (Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads){
            thread.join();
        }

        System.out.println("PI :  " + countPINumber.getPI());
        long endTime = System.nanoTime();
        System.out.print("Time : ");
        System.out.print((endTime - startTime) / 1000000.0);
        System.out.println(" ms");
        System.out.println();
    }

}
