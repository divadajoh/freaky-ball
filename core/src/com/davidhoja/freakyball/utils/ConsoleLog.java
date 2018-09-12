package com.davidhoja.freakyball.utils;

public class ConsoleLog {
// == PRIMITIVE VARIABLES == //
	private static String oldTitle = "";
	
//  == INSTANCE VARIABLES == //
	static StringBuilder console = new StringBuilder("");
	
	
	public static void add(String title, String message) {
		if(! oldTitle.equals(title)) {
			console.append(title.toUpperCase() + " : " + message + ".\n");
			oldTitle = title;
		}
	}
	
	public static void show() {
		System.out.print(console);
	}
	

}
