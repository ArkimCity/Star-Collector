﻿/*
CREATE TABLE probono_project (
	   probono_project_id     		NUMBER(5) PRIMARY KEY,
	   probono_project_name 		VARCHAR2(50) NOT NULL,
       probono_id           			VARCHAR2(50) NOT NULL,       
       activist_id          				VARCHAR2(20) NOT NULL,
       receive_id           				VARCHAR2(20) NOT NULL, 
       project_content      			VARCHAR2(100) NOT NULL
);   */
package probono.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import probono.model.dto.ActivistDTO;
import probono.model.dto.ProbonoDTO;
import probono.model.dto.ProbonoProjectDTO;
import probono.model.dto.RecipientDTO;
import probono.model.util.DBUtil;
import probono.model.util.PublicCommon;

public class ProbonoProjectDAO {
	
	//프로보노 프로젝트 저장
	public static boolean addProbonoProject(ProbonoProjectDTO probonoProject) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into probono_project values(probono_project_id_seq.nextval, ? ?, ?, ?, ?)");
			pstmt.setString(1, probonoProject.getProbonoProjectName());
			pstmt.setString(2, probonoProject.getProbonoId().getProbonoId());
			pstmt.setString(3, probonoProject.getActivistId().getActivistId());
			pstmt.setString(4, probonoProject.getRecipientId().getRecipientId());
			pstmt.setString(5, probonoProject.getProjectContent());
			
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
	// 프로보노 프로젝트 ID로  재능기부자 수정
	public static boolean updateProbonoProjectActivist(int projectId, String activistId) throws SQLException{		
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement("update probono_project set activist_id=? where probono_project_id=?");
			pstmt.setString(1, activistId);
			pstmt.setInt(2, projectId);
			
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
	//프로보노 프로젝트 id로 수해자 정보 변경
	public static boolean updateProbonoProjectReceive(int probonoProjectId, String  receiveId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement("update probono_project set receive_id=? where probono_project_id=?");
			pstmt.setString(1, receiveId);
			pstmt.setInt(2, probonoProjectId);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean deleteProbonoProject(int probonoProjectId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from probono_project where probono_project_id=?");
			pstmt.setInt(1, probonoProjectId);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	//프로보노 프로젝트 id로 해당 프로보노프로젝트 검색
	public static ProbonoProjectDTO getProbonoProject(int probonoProjectId) throws SQLException{
		EntityManager em = PublicCommon.getEntityManger();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProbonoProjectDTO probonoUser = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from probono_project where probono_project_id=?");
			pstmt.setInt(1, probonoProjectId);
			rset = pstmt.executeQuery();
			if(rset.next()){
				probonoUser = ProbonoProjectDTO.builder().probonoProjectId(rset.getInt(1)).probonoProjectName(rset.getString(2)).probonoId(em.find(ProbonoDTO.class, rset.getString(3)))
						.activistId(em.find(ActivistDTO.class, rset.getString(4))).recipientId(em.find(RecipientDTO.class, rset.getString(5))).projectContent(rset.getString(6)).build();
						}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return probonoUser;
	}
	
	//모든 프로보노 프로젝트 검색 
	public static ArrayList<ProbonoProjectDTO> getAllProbonoProjects(EntityManager em) throws SQLException{
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProbonoProjectDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from probono_project");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<ProbonoProjectDTO>();
			while(rset.next()){
				list.add(ProbonoProjectDTO.builder().probonoProjectId(rset.getInt(1)).probonoProjectName(rset.getString(2)).probonoId(em.find(ProbonoDTO.class, rset.getString(3)))
						.activistId(em.find(ActivistDTO.class, rset.getString(4))).recipientId(em.find(RecipientDTO.class, rset.getString(5))).projectContent(rset.getString(6)).build());
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
