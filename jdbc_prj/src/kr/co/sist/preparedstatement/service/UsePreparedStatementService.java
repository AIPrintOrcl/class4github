package kr.co.sist.preparedstatement.service;

import static java.lang.Integer.parseInt; //static import

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.preparedstatement.dao.UsePreparedStatementDAO;
import kr.co.sist.vo.CpEmp1VO;

/**
 * XxxService : 업무로직(Business Logic)을 처리하는 클래스 업무를 처리하기 위한 연산을 수행하고, 처리결과를 DB에
 * 저장하거나 얻기 위해 DAO단의 클래스를 사용한다.
 * 
 * 매우 중요!!!! method명에 쿼리문을 넣어 만들지 말것!!! XxxxService의 method는 업무를 뜻하는 용어로 처리한다.
 * 
 * @author user
 *
 */
public class UsePreparedStatementService {

	/**
	 * 1. 사원번호, 사원명, 부서번호, 부서명을 입력받아 2. VO에 저장하고 3. DAO를 생성하여 DB에 insert 작업을 수행한다.
	 */
	public void addCpEmp1() throws NullPointerException, NumberFormatException {
		String inputData = JOptionPane.showInputDialog("사원번호, 사원명, 부서번호, 매니저번호를 ','로 구분하여 입력해 주세요");
		String[] csvArr = inputData.split(",");

		//////////// 유효성 검증 후 //////////////////////
		if (csvArr.length != 4) {
			JOptionPane.showMessageDialog(null, "입력값의 갯수는 4개 이어야합니다.");
			// early return : 유효성에 위배되면 아랫줄의 코드를 실행하지 않고 호출한 곳으로 돌아간다.
			return;
		} // end if
		// 1.else를 사용하지 않았지만 else에 해당하는 코드를 정의
		// 값은 4개가 입력 되었다.
		int empno = Integer.parseInt(csvArr[0]);
		String ename = csvArr[1];
		int deptno = Integer.parseInt(csvArr[2]);
		int mgr = Integer.parseInt(csvArr[3]);

		// 2.처리된 값을 VO에 넣어서 생성한다.
		CpEmp1VO ceVO=new CpEmp1VO(empno, deptno, mgr, ename, null);
		
		// 3. DAO 클래스를 생성하여 DBMS에 추가 작업을 수행.
		UsePreparedStatementDAO upsDAO=UsePreparedStatementDAO.getInstens();
		try {
			upsDAO.insertCpEmp(ceVO);
			JOptionPane.showMessageDialog(null, ceVO.getEname() + "사원정보가 추가되었습니다.");
		} catch (SQLException se) {
			//JOptionPane.showMessageDialog(null, ceiVO.getEname() + "사원정보가 추가되지 않았습니다.");
			int errorCode = se.getErrorCode();//DBMS의 에러코드를 얻을 수 있다.
			String errMsg="";
			
			if(errorCode == 1) {
				errMsg=empno+"번 사원은 이미 존재합니다.";
			}//end if
			if(errorCode == 1438) {
				errMsg="사원번호는 4자리까지 부서번호는 2자리까지, 매니저번호는 4자리까지만 입력가능합니다.";
			}//end if
			if(errorCode == 12899) {
				errMsg="입력한 사원명 ["+ename+"]은 한글3자, 영문 10를 초과하였습니다. \n한글은 3자까지만, 영문은 10자까지만 입력 가능합니다.";
			}//end if
			JOptionPane.showMessageDialog(null, errMsg+" / "+errorCode);
			se.printStackTrace();
		} // end catch

	}// addCpEmp1

	public void modifyCpEmp1() throws NumberFormatException {
		// 1.변경할 사원번호, 사원명, 부서번호를 입력 받아
		String inputData = JOptionPane.showInputDialog("변경할 사원번호, 사원명, 부서번호를 ','로 구분하여 입력하세요.");

		if (inputData == null) {// 취소나 x버튼을 눌러서 종료한 경우이므로 아래의 코드를 실해하지 않는다.
			return;
		} // if end

		String[] csvArr = inputData.split(",");
		if (csvArr.length != 3) {// 입력값이 3개가 아닌 경우 아래의 코드를 실행하지 않는다.
			JOptionPane.showMessageDialog(null, "사원번호, 사원명, 부서번호를 정확하게 입력해주세요.");
			return;
		} // end if

		// 2. VO에 저장하고 (값을 묶어서 저장)
//		CpEmp1UpdateVO ceuVO =
//				new CpEmp1UpdateVO(parseInt(csvArr[0]), parseInt(csvArr[2]), csvArr[1]);
		
		//int empno, int deptno, int mgr, String ename, Date hiredate
		CpEmp1VO ceVO=new CpEmp1VO(parseInt(csvArr[0]), parseInt(csvArr[2]), 0, csvArr[1], null);
		
		
		// 3. DAO를 사용하여 DBMS table에 변경작업을 수행
		UsePreparedStatementDAO upsDAO=UsePreparedStatementDAO.getInstens();

		// 4. 수행된 결과를 받아와서 화면을 만든다.
		try {
			int rowCnt=upsDAO.updateCpEmp(ceVO);

			String printMsg = ceVO.getEmpno() + "번 사원은 존재하지 않습니다. 사원번호를 확인하세요.";
			
			if(rowCnt==1) {//변경된 행의 수가 한 행이라면(변경된 레코드가 1행)
				printMsg=ceVO.getEmpno()+"번 사원 정보가 "+rowCnt+"건 변경 되었습니다.";
			}//end if
			
			
			JOptionPane.showMessageDialog(null, printMsg);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "일시적으로 문제가 발생하였습니다. \n잠시 후 다시 시도해 주세요.");
			e.printStackTrace();
		} // end catch

	}// modifyCpEmp1

	public void removeCpEmp1() throws NumberFormatException {
		//1.사원번호를 받아
		String inputData=JOptionPane.showInputDialog("삭제할 사원번호를 입력해 주세요.");
		
		if(inputData == null) {//취소를 누르거나, x를 눌렀을 때 아래 코드를 실행하지 않는다.
			return;
		}//end if
		
		int empno=parseInt(inputData);
		//2.DAO 생성하고 <= 싱글톤 : new를 하지 않는다., 삭제 작업을 수행
		UsePreparedStatementDAO upDAO=UsePreparedStatementDAO.getInstens();
		
		try {
			//3.삭제된 레코드의 수를 받아서 결과화면을 만든다.
			int deleteCnt=upDAO.delecteCpEmp(empno);
			String msg=empno+"번 사원은 존재하지 않습니다.";//deleteCnt가 0인 상태의 메시지
			
			if(deleteCnt == 1) {
				msg=empno+"번 사원 정보가 " + deleteCnt + "건 삭제 되었습니다.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
			
		} catch (SQLException e) {//DB에서 문제가 발생했을 때. 1.쿼리문이 잘못된 경우. 2.DB에 문제가 생긴 경우 3. Network에 문제가 생긴 경우
			JOptionPane.showMessageDialog(null, "문제가 발생하였습니다. \n 잠시 후 다시 시도해 주세요.");
			e.printStackTrace();
		}//end catch
	}// removeCpEmp1
	
	public void searchAllCpEmp1(){
		//1.DAO클래스를 객체화하고, 조회결과를 받는다.
		UsePreparedStatementDAO upsDAO=UsePreparedStatementDAO.getInstens();
		//2.조회 결과를 보여준다.
		try {
			//cp_emp1 테이블 모든 레코드를 조회한 결과를 가진 List를 저장한다.
			List<CpEmp1VO> list=upsDAO.selectAllCpEmp1();
			
			StringBuilder outData=new StringBuilder();
			outData.append("사원번호\t사원명\t부서번호\t매니저번호\t입사일\n");
			
			//조회된 레코드가 없을 때의 처리.
			if(list.isEmpty()) {
				outData.append("조회결과가 없습니다.");
			}//end if
			
			//리스트에 존재하는 VO를 꺼내와서 StringBuilder저장
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date=null;
			for(CpEmp1VO cesVO : list) {
				outData
				.append(cesVO.getEmpno()).append("\t")
				.append(cesVO.getEname()).append("\t")
				.append(cesVO.getDeptno()).append("\t")
				.append(cesVO.getMgr()).append("\t");
				date="";
				if(cesVO.getHiredate() != null) {//조회된 날짜 있음
					date=sdf.format(cesVO.getHiredate());
				}//end if
				outData.append(date).append("\n");
			}//end for
			
			JTextArea jta=new JTextArea(outData.toString(), 10, 40);
			JScrollPane jsp=new JScrollPane(jta);
			
			JOptionPane.showMessageDialog(null, jsp);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
			
	}//searchAllCpEmp1
	
	public void searchOneCpEmp1() {
		//1. 사원번호를 입력받아
		String inputData=JOptionPane.showInputDialog("조회할 사원번호를 입력해 주세요.");
		if(inputData == null) {//취소버튼, x버튼이 눌렸을 때 아랬줄을 실행하지 않는다.
			return;
		}//end if
		
		//2. DAO단의 클래스를 생성하여, 조회작업을 수행하고, 결과를 받는다.
		UsePreparedStatementDAO upsDAO=UsePreparedStatementDAO.getInstens();
		//3. 조회결과를 보여준다.
		try {
			CpEmp1VO ceoVO=upsDAO.seletOneCpEmp1(parseInt(inputData));
			
			StringBuilder outData=new StringBuilder();
			outData.append(inputData).append("번 사원 조회 결과\n");
			
			if(ceoVO == null) {//조회된 사원이 존재하지 않는 경우
				outData.append("해당 사원은 존재하지 않습니다. 사원번호를 확인하세요.");
			}else {//조회된 사원이 존재하는 경우
				outData.append("사원명 :").append(ceoVO.getEname()).append("\n");
				outData.append("부서번호 :").append(ceoVO.getDeptno()).append("\n");
				outData.append("매니저번호 :").append(ceoVO.getMgr()).append("\n");
				
				SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy EEEE");
				outData.append("입사일 :").append(sdf.format(ceoVO.getHiredate())).append("\n");
			}//end else
			
			JOptionPane.showMessageDialog(null, outData.toString());
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "사원번호는 숫자형태이어야합니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "서비스 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
			e.printStackTrace();
		}//end catch
			
	}//searchOneCpEmp1

	public static void main(String[] args) {
		UsePreparedStatementService uss = new UsePreparedStatementService();
//		try {
//			uss.addCpEmp1();
//		}catch (NullPointerException npe) {
//			npe.printStackTrace();
//		}catch (NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(null, "사원번호, 부서번호, 매니저번호는 숫자로만 구성됩니다.");
//			nfe.printStackTrace();
//		}//end catch
		
//		try {
//		uss.modifyCpEmp1();
//		}catch(NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(null, "사원번호,부서번호는 숫자로만 구성됩니다.");
//			nfe.printStackTrace();
//		}//end catch
		
//		try {
//			uss.removeCpEmp1();
//		} catch (NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(null, "사원번호는 숫자로만 구성됩니다.");
//			nfe.printStackTrace();
//		} // end catch
		
//		uss.searchAllCpEmp1();
		
		uss.searchOneCpEmp1();

	}// main

}// class
