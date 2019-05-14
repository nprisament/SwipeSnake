package noahprisament.snake;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by student on 7/27/2016.
 */
public class OnSwipeListener extends GestureDetector.SimpleOnGestureListener{

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // Grab two events located on the plane at e1=(x1, y1) and e2=(x2, y2)
        // Let e1 be the initial event
        // e2 can be located at 4 different positions, consider the following diagram
        // (Assume that lines are separated by 90 degrees.)
        //
        //
        //         \ A  /
        //          \  /
        //       D   e1   B
        //          /  \
        //         / C  \
        //
        // So if (x2,y2) falls in region:
        //  A => it's an UP swipe
        //  B => it's a RIGHT swipe
        //  C => it's a DOWN swipe
        //  D => it's a LEFT swipe
        //

        float x1 = e1.getX();
        float y1 = e1.getY();

        float x2 = e2.getX();
        float y2 = e2.getY();

        Direction direction = getDirection(x1, y1, x2, y2);

        int x = 0;
        if (onSwipe(direction, 1)) {
            if(GameLoop.currentDirection == 3){
                GameLoop.pause = true;
            }else {
                GameLoop.currentDirection = 1;
            }
            x = 1;
        } else if (onSwipe(direction, 2)) {
            if(GameLoop.currentDirection == 4){
                GameLoop.pause = true;
            }else {
                GameLoop.currentDirection = 2;
            }
            x = 2;
        } else if (onSwipe(direction, 3)) {
            if(GameLoop.currentDirection == 1){
                GameLoop.pause = true;
            }else {
                GameLoop.currentDirection = 3;
            }
            x = 3;
        } else if (onSwipe(direction, 4)) {
            if(GameLoop.currentDirection == 2){
                GameLoop.pause = true;
            }else {
                GameLoop.currentDirection = 4;
            }
            x = 4;
        }
        return onSwipe(direction, x);
    }

    public boolean onSwipe(Direction direction, int direct){
        if(direct == 1 & direction == Direction.left){
            return true;
        }else if(direct == 2 & direction == Direction.up){
            return true;
        }else if(direct == 3 & direction == Direction.right){
            return true;
        }else if(direct == 4 & direction == Direction.down){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Given two points in the plane p1=(x1, x2) and p2=(y1, y1), this method
     * returns the direction that an arrow pointing from p1 to p2 would have.
     * @param x1 the x position of the first point
     * @param y1 the y position of the first point
     * @param x2 the x position of the second point
     * @param y2 the y position of the second point
     * @return the direction
     */
    public Direction getDirection(float x1, float y1, float x2, float y2){
        double angle = getAngle(x1, y1, x2, y2);
        return Direction.get(angle);
    }

    /**
     *
     * Finds the angle between two points in the plane (x1,y1) and (x2, y2)
     * The angle is measured with 0/360 being the X-axis to the right, angles
     * increase counter clockwise.
     *
     * @param x1 the x position of the first point
     * @param y1 the y position of the first point
     * @param x2 the x position of the second point
     * @param y2 the y position of the second point
     * @return the angle between two points
     */
    public double getAngle(float x1, float y1, float x2, float y2) {

        double rad = Math.atan2(y1-y2,x2-x1) + Math.PI;
        return (rad*180/Math.PI + 180)%360;
    }


    public enum Direction{
        up,
        down,
        left,
        right;

        /**
         * Returns a direction given an angle.
         * Directions are defined as follows:
         *
         * Up: [45, 135]
         * Right: [0,45] and [315, 360]
         * Down: [225, 315]
         * Left: [135, 225]
         *
         * @param angle an angle from 0 to 360 - e
         * @return the direction of an angle
         */
        public static Direction get(double angle){
            if(inRange(angle, 45, 135)){
                return Direction.up;
            }
            else if(inRange(angle, 0,45) || inRange(angle, 315, 360)){
                return Direction.right;
            }
            else if(inRange(angle, 225, 315)){
                return Direction.down;
            }
            else{
                return Direction.left;
            }

        }

        /**
         * @param angle an angle
         * @param init the initial bound
         * @param end the final bound
         * @return returns true if the given angle is in the interval [init, end).
         */
        private static boolean inRange(double angle, float init, float end){
            return (angle >= init) && (angle < end);
        }
    }

    public boolean onSwipeRight() {
        return false;
    }

    public boolean onSwipeLeft() {
        return false;
    }

    public boolean onSwipeTop() {
        return false;
    }

    public boolean onSwipeBottom() {
        return false;
    }
}