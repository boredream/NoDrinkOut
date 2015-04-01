package com.boredream.nodrinkout.utils;

public class MapUtils {
	/**
	 * ��������֮�����
	 * 
	 * @param start
	 * @param end
	 * @return ��
	 */
	public static double getDistance(double startLatitude, double startLongitude,
			double endLatitude, double endLongitude) {
		double lat1 = (Math.PI / 180) * startLatitude;
		double lat2 = (Math.PI / 180) * endLatitude;

		double lon1 = (Math.PI / 180) * startLongitude;
		double lon2 = (Math.PI / 180) * endLongitude;

		// ����뾶
		double R = 6371;

		// �������� km�������Ҫ�׵Ļ������*1000�Ϳ�����
		double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1)
				* Math.cos(lat2) * Math.cos(lon2 - lon1))
				* R;

		return d * 1000;
	}
}
