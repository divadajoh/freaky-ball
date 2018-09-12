package com.davidhoja.freakyball.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class FileHandler {
	
//  == INSTANCE VARIABLES == //
	public static Preferences prefs;
	
//  == PRIMITIVE VARIABLES == //
	private boolean isSoundOn;
	private boolean controlWithTouch;
	
	
	
	
//	== CONSTRUCTOR == //
	static {
		prefs = Gdx.app.getPreferences("prefs");
	}
	
//  == RETURNS A SAVED BOOLEAN VARIABLE == //
	public static boolean getBooleanValue(String valueID) {
		boolean returnVariable = false;
		try{
			
			returnVariable = prefs.getBoolean(valueID);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return returnVariable;
	}
	
	
//  == SAVES A BOOLEAN VALUE == //
	public static void putBooleanValue(String valueID, boolean value) {
		try{
			prefs.putBoolean(valueID, value);
			prefs.flush();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	

}
