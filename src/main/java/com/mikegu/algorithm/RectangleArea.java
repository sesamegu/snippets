package com.mikegu.algorithm;

/**
 * Introduction: the solution to the the problem:  https://leetcode-cn.com/problems/rectangle-area/
 *
 * @author sesame 2021/9/30
 */
public class RectangleArea {


    public static void main(String[] args) {
        RectangleArea rectangleArea = new RectangleArea();
        int result = rectangleArea.computeArea(-5,
            -2,
            5,
            1,
            -3,
            -3,
            3,
            3);
        System.out.println("the result is " + result);

//        result = rectangleArea.computeArea(-2, -2, 2, 2, -2, -2, 2, 2);
//        System.out.println("the result is " + result);

    }

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {

        int total = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);

        int aOverlapB = oneInAnother(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
        if (aOverlapB != 0) {
            return total - aOverlapB;
        }
        int bOverlapA = oneInAnother(bx1, by1, bx2, by2, ax1, ay1, ax2, ay2);
        return total - bOverlapA;
    }

    /**
     * @return the area that are overlap
     */
    public int oneInAnother(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        Point leftBottom = new Point(ax1, ay1);
        Point rightBottom = new Point(ax2, ay1);
        Point leftTop = new Point(ax1, ay2);
        Point rightTop = new Point(ax2, ay2);

        boolean isLeftBottomIn = isPointInRectangle(leftBottom, bx1, by1, bx2, by2);
        boolean isRightBottomIn = isPointInRectangle(rightBottom, bx1, by1, bx2, by2);
        boolean isLeftTopIn = isPointInRectangle(leftTop, bx1, by1, bx2, by2);
        boolean isRightTopIn = isPointInRectangle(rightTop, bx1, by1, bx2, by2);

        //four points
        if (isLeftBottomIn && isRightBottomIn && isLeftTopIn && isRightTopIn) {
            return (ax2 - ax1) * (ay2 - ay1);
        }

        //two points
        if (isLeftBottomIn && isRightBottomIn) {
            return (ax2 - ax1) * (by2 - ay1);
        }
        if (isLeftBottomIn && isLeftTopIn) {
            return (bx2 - ax1) * (ay2 - ay1);
        }
        if (isLeftTopIn && isRightTopIn) {
            return (ax2 - ax1) * (ay2 - by1);
        }
        if (isRightBottomIn && isRightTopIn) {
            return (ax2 - bx1) * (ay2 - ay1);
        }

        //one point
        if (isLeftBottomIn) {
            return (bx2 - ax1) * (by2 - ay1);
        }
        if (isRightBottomIn) {
            return (ax2 - bx1) * (by2 - ay1);
        }
        if (isLeftTopIn) {
            return (bx2 - ax1) * (ay2 - by1);
        }
        if (isRightTopIn) {
            return (ax2 - bx1) * (ay2 - by1);
        }

        // no point but overlap
        if (ax1 < bx1 && ax2 > bx2 && ay1 > by1 && ay2 < by2) {
            return (bx2 - bx1) * (ay2 - ay1);
        }

        return 0;
    }

    public boolean isPointInRectangle(Point point, int bx1, int by1, int bx2, int by2) {
        if (point.getX() >= bx1 && point.getX() <= bx2 && point.getY() >= by1 && point.getY() <= by2) {
            return true;
        }
        return false;
    }


    class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }


}
