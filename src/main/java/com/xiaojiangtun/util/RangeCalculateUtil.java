package com.xiaojiangtun.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RangeCalculateUtil {

	// 平均半径,单位：m
    private static final double EARTH_RADIUS = 6371393;
    
    /**
     * 通过AB点经纬度获取距离
     * @param pointA A点(经，纬)
     * @param pointB B点(经，纬)
     * @return 距离(单位：米)
     */
    public static double getDistance(Point2D pointA, Point2D pointB) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
		// A经弧度
        double radiansAX = Math.toRadians(pointA.getX());
		// A纬弧度
        double radiansAY = Math.toRadians(pointA.getY());
		// B经弧度
        double radiansBX = Math.toRadians(pointB.getX());
		// B纬弧度
        double radiansBY = Math.toRadians(pointB.getY());
 
        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
//        System.out.println("cos = " + cos); // 值域[-1,1]
		// 反余弦值
        double acos = Math.acos(cos);
//        System.out.println("acos = " + acos); // 值域[0,π]
//        System.out.println("∠AOB = " + Math.toDegrees(acos)); // 球心角 值域[0,180]
		// 最终结果
		return EARTH_RADIUS * acos;
    }
	
	
	/**
	 * 判断点是否在多边形内
	 * @param point 检测点
	 * @param pts   多边形的顶点
	 * @return      点在多边形内返回true,否则返回false
	 */
	public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts){
	    
	    int N = pts.size();
		//如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
	    boolean boundOrVertex = true;
	    int intersectCount = 0;
		//浮点类型计算时候与0比较时候的容差
	    double precision = 2e-10;
		//neighbour bound vertices
	    Point2D.Double p1, p2;
		//当前点
	    Point2D.Double p = point;

		//left vertex
	    p1 = pts.get(0);
		//check all rays
	    for(int i = 1; i <= N; ++i){
	        if(p.equals(p1)){
				//p is an vertex
	            return boundOrVertex;
	        }

			//right vertex
	        p2 = pts.get(i % N);
			//ray is outside of our interests
	        if(p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)){
	            p1 = p2;
				//next ray left point
	            continue;
	        }

			//ray is crossing over by the algorithm (common part of)
	        if(p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)){
				//x is before of ray
	            if(p.y <= Math.max(p1.y, p2.y)){
					//overlies on a horizontal ray
	                if(p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)){
	                    return boundOrVertex;
	                }

					//ray is vertical
	                if(p1.y == p2.y){
						//overlies on a vertical ray
	                    if(p1.y == p.y){
	                        return boundOrVertex;
							//before ray
	                    }else{
	                        ++intersectCount;
	                    }
						//cross point on the left side
	                }else{
						//cross point of y
	                    double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;
						//overlies on a ray
	                    if(Math.abs(p.y - xinters) < precision){
	                        return boundOrVertex;
	                    }

						//before ray
	                    if(p.y < xinters){
	                        ++intersectCount;
	                    } 
	                }
	            }
				//special case when ray is crossing through the vertex
	        }else{
				//p crossing over p2
	            if(p.x == p2.x && p.y <= p2.y){
					//next vertex
	                Point2D.Double p3 = pts.get((i+1) % N);
					//p.x lies between p1.x & p3.x
	                if(p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)){
	                    ++intersectCount;
	                }else{
	                    intersectCount += 2;
	                }
	            }
	        }
			//next ray left point
	        p1 = p2;
	    }

		//偶数在多边形外
	    if(intersectCount % 2 == 0){
	        return false;
			//奇数在多边形内
	    } else {
	        return true;
	    }
	    
	}


	/**
	 * 测试一个点是否在多边形内
	 * @param args
	 */
	public static void main(String[] args) {
		Point2D.Double point = new Point2D.Double(23.132407, 113.322860);
		Point2D.Double point2 = new Point2D.Double(23.13235, 113.32257);
		double distance = RangeCalculateUtil.getDistance(point, point2);
	    System.out.println(distance);
	    
	    List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
	    pts.add(new Point2D.Double(23.122951,113.247183));
	    pts.add(new Point2D.Double(23.121352,113.247086));
	    pts.add(new Point2D.Double(23.121259,113.248326));
	    pts.add(new Point2D.Double(23.122694,113.248277));
	    
	    if(IsPtInPoly(point, pts)){
	        System.out.println("点在多边形内");
	    }else{
	        System.out.println("点在多边形外");
	    }
	}
}
