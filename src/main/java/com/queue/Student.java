package com.queue;

public class Student {
	private int age = 1;
	private String name;
	private int sex=1;
	public void study(){
		System.out.println("xuexi");
	}
	public Student(){
		
	}
	public Student(int _i){
		this.age = _i;
		this.sex = _i;
	}
	@Override
	public String toString(){
		return "age: "+age+"sex : "+sex+"name: zhang";
	}
	
}
