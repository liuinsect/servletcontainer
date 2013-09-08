package com.liusoft.exception;

public class ExceptionFactory {
	
	public static RuntimeException throwRunTimeException(String message){
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		
		StackTraceElement topPre = trace[ trace.length - 1 ];
		RuntimeException re = new RuntimeException( topPre.getClassName() + "." + topPre.getMethodName() + "has exception : " + message);
		return re;
	}
}
