package CarloMethod;

import CarloMethod.model.Point;

import java.util.Random;

public class PointFinder {
    private Random random = new Random();
    private int radius;

    public PointFinder(int radius){
        this.radius = radius;
    }

    public boolean checkPoint (){
        return checkPointPosition(getPoint());
    }

    private double getRandom() {
        return random.nextDouble();
    }

    private Point getPoint (){
        Point point = new Point();
        double xPoint = getRandom() * (2 * radius) - radius;
        double yPoint = getRandom() * (2 * radius) - radius;
        point.setXPoint(xPoint);
        point.setYPoint(yPoint);
        return point;
    }

    private boolean checkPointPosition (Point point){
        double distanceFromCenter ;
        distanceFromCenter = Math.sqrt((point.getXPoint() * point.getXPoint()) + (point.getYPoint() * point.getYPoint()));
        return distanceFromCenter < radius;
    }
}
