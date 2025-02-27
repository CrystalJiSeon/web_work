package com.example.spring10.exception;

//거부된 요청일 때 발생시킬 Exception
public class DeniedException extends RuntimeException{
	public DeniedException(String msg) {
		super(msg);
	}
}
