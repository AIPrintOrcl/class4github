package kr.co.sist.vo;

/**
 * 사원번호, 사원명, 부서번호를 가진 VO
 * @author user
 */
public class CpEmp1UpdateVO {
	private int empno, deptno;
	private String ename;
	
	public CpEmp1UpdateVO() {
		super();
	}

	public CpEmp1UpdateVO(int empno, int deptno, String ename) {
		super();
		this.empno = empno;
		this.deptno = deptno;
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
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	@Override
	public String toString() {
		return "CpEmp1UpdateVO [empno=" + empno + ", deptno=" + deptno + ", ename=" + ename + "]";
	}

}//clas