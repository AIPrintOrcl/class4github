package day0819;

/**
 * Singleton Pattern을 도입한 클래스.
 * 
 * 실행중인 JVM에서 하나의 객체로만 사용할 수 있도록 만드는 클래스.
 *  - 객체를 참조하는 속도가 빠르다.
 *  - 하나의 객체가 생성되고 사용되므로 객체사용비용이 적다.(메로리를 적게 사용)
 *  - 사용도중 객체가 소멸되면, 다시 생성될 수 없다. => 다시 생성되도록 코딩.
 * @author user
 */
public class Singleton {
	
	static Singleton single;//객체가 생성되었는지 판단하기위한 변수(객체)

	//1. 클래스 외부에서 직접 객체화 될 수 없도록 생성자의 접근 지정자를 private으로 만든다.
	private Singleton() {
		
	}//Singleton
	
	//2.생성된 객체를 반환하는 일을 하는 method
	public static Singleton getInstance() {
		//3.객체를 하나로 유지시키는 코드를 작성.
		if(single==null) {//객체 생성되었는지 안다면 : 이유)1.최초호출 2.사용중인 객체가 소멸
			single=new Singleton();
		}//end if
		
		return single;
	}//getInstance
	
}//class
