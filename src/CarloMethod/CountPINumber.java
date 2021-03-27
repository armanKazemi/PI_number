package CarloMethod;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountPINumber implements Runnable{
    private PointFinder pointFinder;
    private int numberOfPointInCircle = 0;
    private int numberOfPointInSquare = 0;
    private double PI;
    private Lock lock = new ReentrantLock();
    private boolean thread = true;
    private int repetition;

    public CountPINumber(int radius, int repetition){
        pointFinder = new PointFinder(radius);
        this.repetition = repetition;
    }

    public void startSingleThread(){
        for ( ; repetition > 0 ; repetition--) {
            numberOfPointInCircle = (pointFinder.checkPoint()) ? numberOfPointInCircle + 1 : numberOfPointInCircle;
            numberOfPointInSquare++;
        }
        PI = countPI();
    }

    public synchronized void run (){

        for (; repetition > 0; repetition--) {
//            lock.lock();
            try {
                numberOfPointInCircle = (pointFinder.checkPoint()) ? numberOfPointInCircle + 1 : numberOfPointInCircle;
                numberOfPointInSquare++;
            } finally {
//                lock.unlock();
            }
//            System.out.println(repetition);
        }

        PI = countPI();
//        System.out.println(PI + " PI nums.");
    }

    private double countPI (){
        return  4.0 * numberOfPointInCircle / numberOfPointInSquare;
    }

    public double getPI() {
        return PI;
    }
}
