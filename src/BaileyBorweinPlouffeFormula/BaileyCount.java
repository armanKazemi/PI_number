package BaileyBorweinPlouffeFormula;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BaileyCount implements Runnable{
    private Lock lock = new ReentrantLock();
    private BigDecimal PI = new BigDecimal(0);
    private int repetition;
    private int threadNumber, threadIndex = 0;

    public BaileyCount (int repetition, int threadNumber){
        this.repetition = repetition;
        this.threadNumber = threadNumber;
    }

    public void countPI (){
        for (int counter = 0 ; counter <= repetition ; counter++){
            PI = PI.add(countStatement(counter));
        }
    }

    @Override
    public void run (){
        lock.lock();
        try{
            for (int counter = 0; counter <= repetition; counter++) {
                PI = PI.add(countStatement(counter));
                printPI();
            }
        }finally {
            lock.unlock();
        }

    }

    private void printPI(){
        lock.lock();
        if (++threadIndex == threadNumber) {
            System.out.println("PI :  " + getPI());
        }
        lock.unlock();
    }

    private BigDecimal countStatement (int side){
        double forResult = 1.0 , result;
        for (int counter = 0; counter < side ; counter++){
            forResult *= 16;
        }
        result = (1.0 / forResult) * ((4.0 / (8.0 * side + 1.0)) - (2.0 / (8.0 * side + 4.0)) - (1.0 / (8.0 * side + 5.0)) - (1.0 / (8.0 * side + 6.0)));
        return new BigDecimal(result,  MathContext.DECIMAL64);
    }

    public BigDecimal getPI() {
        return PI;
    }
}
