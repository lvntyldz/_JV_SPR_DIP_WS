package com.okan.imageproccesing.hwlast.util;

public class Point implements Comparable<Point> {
	private double x;
	private double y;
	private Point comparatorPoint = null;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public static boolean isCounterclockwise(Point a, Point b, Point c) {
		double signed_area_doubled = (b.x - a.x) * (c.y - a.y) - (b.y - a.y)
				* (c.x - a.x);
		return (signed_area_doubled > 0);
	}


	public void setComparatorPoint(Point p) {
		this.comparatorPoint = p;
	}


	public double getPolarAngle() {
		double arctan = Math.atan2(y, x);
		return (arctan >= 0) ? arctan : (Math.PI * 2 - arctan);
	}

	public double getPolarAngle(Point p) {
		double x_n = p.x - x;
		double y_n = p.y - y;
		return new Point(x_n, y_n).getPolarAngle();
	}


	@Override
	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point p = (Point) o;
			return p.x == x && p.y == y;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Point p) {
		if (comparatorPoint == null)
			comparatorPoint = new Point(0, 0);
		Double angle1 = comparatorPoint.getPolarAngle(this);
		Double angle2 = comparatorPoint.getPolarAngle(p);
		return angle1.compareTo(angle2);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
