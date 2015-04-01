package com.boredream.nodrinkout.utils;

public class MapUtils {
	/**
	 * 计算两点之间距离
	 * 
	 * @param start
	 * @param end
	 * @return 米
	 */
	public static double getDistance(double startLatitude, double startLongitude,
			double endLatitude, double endLongitude) {
		double lat1 = (Math.PI / 180) * startLatitude;
		double lat2 = (Math.PI / 180) * endLatitude;

		double lon1 = (Math.PI / 180) * startLongitude;
		double lon2 = (Math.PI / 180) * endLongitude;

		// 地球半径
		double R = 6371;

		// 两点间距离 km，如果想要米的话，结果*1000就可以了
		double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1)
				* Math.cos(lat2) * Math.cos(lon2 - lon1))
				* R;

		return d * 1000;
	}
}
