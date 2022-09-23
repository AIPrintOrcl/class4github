package day0819;

public class UseSingleton {

	public static void main(String[] args) {
		/*
		 클래스외부에서 객체화를 하면 객체가 몇개나 생성될지 알 수 없으며 제어할 수 없다.
		 Singleton s=new Singleton();
		 Singleton s1=new Singleton();
		 Singleton s2=new Singleton();
		 */
//		Singleton s=new Singleton();//private 이어서 객체화 할 수 없다.
		
		Singleton s=Singleton.getInstance();
		Singleton s2=Singleton.getInstance();
		Singleton s3=Singleton.getInstance();
		
		System.out.println(s);
		System.out.println(s2);
		System.out.println(s3);
		
		
	}//main

}//class
