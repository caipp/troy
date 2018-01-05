package com.lingling.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datas {
	
	public static String show(long l){
		
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l));
	}
}
