package kr.co.sist.vo;

/**
 * VO (Value Object)
 * 값을 저장하여, 객체간에 전달할 목적으로 만드는 클래스.
 * DBMS 테이블의 컬럼과 같은 이름으로 인스턴스 변수를 만들고, 
 * setter method, getter method를 정의한다.
 * @author user
 */
public class CpEmp1InsertVO {
	private int empno, deptno, mgr;	
	private String ename;
	
	public CpEmp1InsertVO() {
		super();
	}
	public CpEmp1InsertVO(int empno, int deptno, int mgr, String ename) {
		super();
		this.empno = empno;
		this.deptno = deptno;
		this.mgr = mgr;
		this.ename = ename;
	}
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	@Override
	public String toString() {
		return "CpEmp1InsertVO [empno=" + empno + ", deptno=" + deptno + ", mgr=" + mgr + ", ename=" + ename + "]";
	}
	
}//class
