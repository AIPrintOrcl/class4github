package day0822;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.common.dao.DbConnection;

public class TableInfoDAO {

	public List<ColumnVO> selectAllColumn(String tableName) throws SQLException{
		List<ColumnVO> list = new ArrayList<ColumnVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection db=DbConnection.getInstance();
		
		try {
			con=db.getConn();
			
			StringBuilder selectTable=new StringBuilder();
			
			selectTable
			.append("	select	column_name, data_type, data_length	")
			.append("	from	user_tab_cols						")
			.append("	where	table_name=?						");
			
			pstmt=con.prepareStatement(selectTable.toString());
			
			//4.
			pstmt.setString(1, tableName);
			//5.
			rs=pstmt.executeQuery();
			
			ColumnVO coVO=null;
			while(rs.next()) {
				coVO=new ColumnVO();
				coVO.setColumn_name(rs.getString("column_name"));
				coVO.setData_type(rs.getString("data_type"));
				coVO.setData_length(rs.getInt("data_length"));
				list.add(coVO);
			}//end while
			
		}finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectAllColumn
	
	public List<String> selectAllTab() throws SQLException{
		List<String> list = new ArrayList<String>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection db=DbConnection.getInstance();
		
		try {
			con=db.getConn();
			
			StringBuilder selectTab=new StringBuilder();
			
			selectTab
			.append("	select	DISTINCT table_name		")
			.append("	from	user_tab_cols			");
			
			pstmt=con.prepareStatement(selectTab.toString());
			
			//5.
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("table_name"));
			}//end while
			
		}finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectAllTab
	
	public static void main(String[] args) {
		TableInfoDAO tiDAO=new TableInfoDAO();
		
		try {
			tiDAO.selectAllTab();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main
	
}//class
