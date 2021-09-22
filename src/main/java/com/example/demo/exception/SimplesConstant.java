package com.example.demo.exception;

public enum SimplesConstant {
	OK("SUCCESS"),
	KO("FAILURE"),
	WRONG("SOMETHING WENT WRONG!!"),
	TECH_ERROR("Please check all fields"),
	CODE_0("0"),
	CODE_1("1"),
	CODE_2("2"),
	ERROR_1("ERROR_1"),
	EXIST("Already Exist");
	
	
	
	
	private String code;
	SimplesConstant(String code){
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}	
	
}
