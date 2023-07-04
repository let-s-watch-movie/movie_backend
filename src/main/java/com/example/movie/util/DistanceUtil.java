package com.example.movie.util;

import com.example.movie.entity.User;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

public class DistanceUtil {
	private static final double earthRadius = 6371.0;
	
	public static double calculateDistance(User A, User B){
		GeodesicData geodesicData = Geodesic.WGS84.Inverse(A.getLatitude(),A.getLongitude(),B.getLatitude(),B.getLongitude());
		double distance = geodesicData.s12 / 1000.0;
		return distance;
	}
	
	public static void main(String ...args){
		User A = new User();
		A.setLatitude(30.34);
		A.setLongitude(114.21);
		User B = new User();
		B.setLatitude(40.31);
		B.setLongitude(108.4);
		
		System.out.println(DistanceUtil.calculateDistance(A,B));
	}
}
