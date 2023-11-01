package com.example.springbootadea.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtilsHelper {

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

	public static boolean esFechaMenorQue(Date a, Date b) {
	   return a.before(b);
	}
}
