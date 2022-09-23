package kr.co.sist.vo;

import java.sql.Date;

public class CpEmp1SelectVO {

	private int empno, deptno, mgr;
	private String ename;
	private Date hiredate;
	
	public CpEmp1SelectVO() {
		super();
	}
	
	public CpEmp1SelectVO(int empno, int deptno, int mgr, String ename, Date hiredate) {
		super();
		this.empno = empno;
		this.deptno = deptno;
		this.mgr = mgr;
		this.ename = ename;
		this.hiredate = hiredate;
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
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	@Override
	public String toString() {
		return "CpEmp1SelectVO [empno=" + empno + ", deptno=" + deptno + ", mgr=" + mgr + ", ename=" + ename
				+ ", hiredate=" + hiredate + "]";
	}
	
}
