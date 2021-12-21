package com.home.spanishplatform.utils;

import java.util.ArrayList;
import java.util.List;

public class NumberUtils {
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}
	
	public static List<Integer> drawDistinctRandomNumbersInRange(int randomMin, int randomMax) {
		List<Integer> trainingNumbers = new ArrayList<Integer>();
		
		int randomRange = randomMax - randomMin + 1;
        int nextTrainingNumber;
        
        while (trainingNumbers.size() < getCountOfNumbers(randomMin, randomMax)) {
        	nextTrainingNumber = (int)(Math.random() * randomRange) + randomMin;
        	if (!trainingNumbers.contains(nextTrainingNumber))
        		trainingNumbers.add(nextTrainingNumber);
        }
        
        return trainingNumbers;
	}
	
	public static List<Integer> drawDistinctSequentialNumbersInRange(int seqMin, int seqMax) {
		List<Integer> trainingNumbers = new ArrayList<Integer>();

        for (int i = 0; i < getCountOfNumbers(seqMin, seqMax); i++) {
        	trainingNumbers.add(seqMin + i);
        }
        
        return trainingNumbers;
	}
	
	public static boolean isRangeNumericAndPositive(String rangeFrom, String rangeTo) {
		if (NumberUtils.isNumeric(rangeFrom) && NumberUtils.isNumeric(rangeTo)) {			
			if (Integer.parseInt(rangeTo) - Integer.parseInt(rangeFrom) >= 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private static int getCountOfNumbers(int numbMin, int numbMax) {
		int rangeDifference = numbMax - numbMin + 1;
		return rangeDifference < 10 ? rangeDifference : 10;
	}
}
