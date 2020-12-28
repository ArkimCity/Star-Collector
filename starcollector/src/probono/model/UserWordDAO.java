/*
 * CREATE TABLE probono (
       probono_id           	VARCHAR2(50) PRIMARY KEY,
       probono_name     	VARCHAR2(50) NOT NULL,
       probono_purpose  VARCHAR2(200) NOT NULL
);  */
package probono.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import probono.model.util.DBUtil;

public class UserWordDAO {	
	
	//저장
	public static boolean saveUserWord(String id, String word) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into userword values(?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, word);
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}


	//수정
	//프로보노 id로 프로보노 목적 수정하기
	public static boolean updateUserWord(String probonoId, String probonoPurpose) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update probono set probono_purpose=? where probono_id=?");
			pstmt.setString(1, probonoPurpose);
			pstmt.setString(2, probonoId);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}


	//삭제 
	public static boolean deleteUserWord(String id, String word) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from userword where userId=? and word=?");
			pstmt.setString(1, id);
			pstmt.setString(2, word);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//해당 유저가 저장한 단어 검색
	public static ArrayList<String> getUserWords(String userId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from userword where userid=?");
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<String>();
			while(rset.next()){
				list.add(rset.getString(2));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
