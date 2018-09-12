package com.davidhoja.freakyball.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectHandler {
	
	private static ObjectInputStream is;

	public ObjectHandler() {
		
	}
	
	public static void saveObject(Object object, String fileName) {
		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(object);
			os.close();
		}catch(Exception ex) {
			ex.printStackTrace();
			
		}
	}
	
	public static Object readObject(String fileName) {
		Object readObject = null;
		try {
			is = new ObjectInputStream(new FileInputStream(fileName));
			readObject = is.readObject();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return readObject;
	}


}
