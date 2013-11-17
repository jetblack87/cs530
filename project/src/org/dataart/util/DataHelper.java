package org.dataart.util;

import java.util.HashMap;

import org.dataart.model.Data;

public class DataHelper {
	public static double normalizeNumber(double newLower, double newUpper, double lowerBound, double upperBound, double number)
	{
    	double b = newUpper;
    	double a = newLower;
		double B = upperBound;
		double A = lowerBound;
		return a + (number - A) * (b-a) / (B-A);
	}

	public static int getLowerBound(Data data, String field){
		int lowerBound = Integer.MAX_VALUE;
		for(HashMap<String, Object> element : data){
			Number value = Data.getNumberValue(element.get(field));
			if(value.intValue() < lowerBound) {
				lowerBound = value.intValue();
			}
	    }    	
		return lowerBound;
	}

	public static int getUpperBound(Data data, String field){
		int upperBound = Integer.MIN_VALUE;
		for(HashMap<String, Object> element : data){
			Number value = Data.getNumberValue(element.get(field));
			if(value.intValue() > upperBound) {
				upperBound = value.intValue();
			}
	    }    	
		return upperBound;
	}
}
