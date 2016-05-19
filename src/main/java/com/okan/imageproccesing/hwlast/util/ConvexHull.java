package com.okan.imageproccesing.hwlast.util;

import com.okan.imageproccesing.hwlast.util.Point;

import java.util.Arrays;

public class ConvexHull {

    private int n;
    private Point[] points;

    public ConvexHull(Point[] points) {
        this.n = points.length;
        this.points = points;
    }

    public Point getPointWithLowestYCoord() {
        Point lowest_point = points[0];
        for (int i = 1; i < n; i++) {
            if (points[i].getY() < lowest_point.getY()) {
                lowest_point = points[i];
            } else if (points[i].getY() < lowest_point.getY()) {
                if (points[i].getX() > lowest_point.getX())
                    lowest_point = points[i];
            }
        }
        return lowest_point;
    }

    public int computeConvexHull() {
        if (n <= 2)
            return n;
        Point point_with_lowest_y_coord = getPointWithLowestYCoord();
        for (int i = 0; i < n; i++) {
            points[i].setComparatorPoint(point_with_lowest_y_coord);
        }
        Arrays.sort(points);
        Point[] mod_points = new Point[n + 1];
        for (int i = 0; i < n; i++) {
            mod_points[i] = points[i];
        }
        mod_points[n] = points[0];
        points = mod_points;
        int convex_hull_index = 1;
        int i = 2;
        while (i <= n) {

            while (!Point.isCounterclockwise(points[convex_hull_index - 1],
                    points[convex_hull_index], points[i])) {
                if (convex_hull_index > 1)
                    convex_hull_index--;
                else if (i == n)
                    break;
                else
                    i++;
            }
            convex_hull_index++;
            swap(points, convex_hull_index, i);
            i++;
        }
        return convex_hull_index;
    }

    public Point[] getConvexHull() {
        int convex_hull_index = computeConvexHull();
        Point[] convex_hull_points = new Point[convex_hull_index];
        for (int i = 0; i < convex_hull_index; i++) {
            convex_hull_points[i] = points[i];
        }
        return convex_hull_points;
    }

    private void swap(Point[] points, int index1, int index2) {
        assert (index1 < points.length);
        assert (index2 < points.length);

        Point aux = points[index1];
        points[index1] = points[index2];
        points[index2] = aux;
    }

}
