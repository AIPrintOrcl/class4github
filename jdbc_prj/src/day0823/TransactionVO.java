package day0823;

public class TransactionVO {
	private String name;
	private int age;
	
	public TransactionVO() {
	
	}
	
	public TransactionVO(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "TransactionVO [name=" + name + ", age=" + age + "]";
	}
	
}
