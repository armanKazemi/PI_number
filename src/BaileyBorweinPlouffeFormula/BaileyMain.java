package BaileyBorweinPlouffeFormula;

import CarloMethod.CountPINumber;

import java.util.ArrayList;

public class BaileyMain {
    public static void main (String[] args) throws InterruptedException {
        System.out.println("This program count PI number with BAILEY BROWEIN PLOUFFE FORMULA, in single thread and multiple thread to compare them.");
        int threadNumber = 4;
        singleThread();
        multipleThread(threadNumber);
    }

    static private void singleThread (){
        long startTime = System.nanoTime();

        System.out.println("Single Thread : ");

        BaileyCount baileyCountSingleThread = new BaileyCount(100000, 0);
        baileyCountSingleThread.countPI();

        long endTime = System.nanoTime();
        System.out.print("Time : ");
        System.out.print((endTime - startTime) / 1000000.0);
        System.out.println(" ms");
        System.out.println("PI :  " + baileyCountSingleThread.getPI());
        System.out.println();
    }

    static private void multipleThread (int threadNumber) throws InterruptedException {
        long startTime = System.nanoTime();

        System.out.println("Multiple Thread : ");

        BaileyCount baileyCountMultipleThread = new BaileyCount(10000, threadNumber);
        ArrayList<Thread> threads = new ArrayList<>();

        for (int counter = 0 ; counter < threadNumber ; counter++){
            threads.add(new Thread(baileyCountMultipleThread));
        }

        for (Thread thread : threads){
            thread.start();
        }

//        for (Thread thread : threads){
//            thread.join();
//        }

        long endTime = System.nanoTime();
        System.out.print("Time : ");
        System.out.print((endTime - startTime) / 1000000.0);
        System.out.println(" ms");
    }
}
