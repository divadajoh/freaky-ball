package com.davidhoja.freakyball.utils;


public class PauseHelper {
	
	private static boolean isPaused;
	private static boolean firstTimeTouched = true;
	private static  long touchBeforeTime;
	private static long touchAfterTime;
	
	
	private int rightClickCount = 0;
	private int leftClickCount = 0;
	
	
	private static void handleBallSpeed (boolean touchedOnRight) {
		touchBeforeTime = System.currentTimeMillis();
		
		
		if(firstTimeTouched) {
			firstTimeTouched = false;
			
			
		}else {
			if(touchBeforeTime - touchAfterTime > 500) {
				
			}
		}
		
		touchAfterTime = System.currentTimeMillis();
	}
	


}
