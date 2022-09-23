package day0823;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.common.dao.DbConnection;

public class Work0823DAO {

	public List<Work0823VO> selectCar(String maker) throws SQLException {
		List<Work0823VO> list=new ArrayList<Work0823VO>();
		
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		
		DbConnection db=DbConnection.getInstance();
		try {
			con=db.getConn();
			
			StringBuilder selectCarWk=new StringBuilder();
			selectCarWk
			.append("	select 	country,maker,model,car_year, price, car_option	")
			.append("	from	(select cc.country, cc.maker, cma.model, cmo.car_year, cmo.price, cmo.car_option	")
			.append("			from CAR_COUNTRY cc, CAR_MAKER cma, CAR_MODEL cmo	")
			.append("			where (cma.maker=cc.maker(+) and cmo.model=cma.model(+) ))")
			.append("	where 	maker=?");
			
			pstmt=con.prepareStatement(selectCarWk.toString());
			
			pstmt.setString(1, maker);
			
			rs=pstmt.executeQuery();
			
			Work0823VO wVO=null;
			while(rs.next()) {
				wVO=new Work0823VO();
				wVO.setCountry(rs.getString("country"));
				wVO.setMaker(rs.getString("maker"));
				wVO.setModel(rs.getString("model"));
				wVO.setCar_year(rs.getString("car_year"));
				wVO.setPrice(rs.getInt("price"));
				wVO.setCar_option(rs.getString("car_option"));
				list.add(wVO);
			}//end while
			
		}finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		return list;
	}//selectCar
	
}//class
