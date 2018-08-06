package com.dinner.comm;
public class CommFunc {
	public static boolean isNotEmpty(String param){
			if(param == null || param == "" ){
				return false;
			}
			return true;
	}
}
