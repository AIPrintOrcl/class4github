package kr.co.sist.vo;

import java.sql.Date;

public class CpEmp1OneVO {
	private int deptno, mgr;
	private String ename, strHiredate;//hiredate를 to_char함수로 원하는 날짜로 만든 후 저장변수
	private Date hiredate;
	
	public CpEmp1OneVO() {
		super();
	}
	
	public CpEmp1OneVO(int deptno, int mgr, String ename, String strHiredate, Date hiredate) {
		super();
		this.deptno = deptno;
		this.mgr = mgr;
		this.ename = ename;
		this.strHiredate = strHiredate;
		this.hiredate = hiredate;
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
	public String getStrHiredate() {
		return strHiredate;
	}
	public void setStrHiredate(String strHiredate) {
		this.strHiredate = strHiredate;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	@Override
	public String toString() {
		return "CpEmp1OneVO [deptno=" + deptno + ", mgr=" + mgr + ", ename=" + ename + ", strHiredate=" + strHiredate
				+ ", hiredate=" + hiredate + "]";
	}
	
}//class
