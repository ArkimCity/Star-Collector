package probono.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.qlrm.mapper.JpaResultMapper;

import lombok.extern.slf4j.Slf4j;
import probono.exception.MessageException;
import probono.exception.NotExistException;
import probono.model.dto.ActivistDTO;
import probono.model.dto.ProbonoDTO;
import probono.model.dto.ProbonoProjectDTO;
import probono.model.dto.RecipientDTO;

@Slf4j

public class ProbonoService {

	//Probono - CRUD
	public static void notExistProbono(String probonoId) throws NotExistException, SQLException{
		ProbonoDTO probono = ProbonoDAO.getProbono(probonoId);
		if(probono == null){
			throw new NotExistException("검색하진 재능기부 정보가 없습니다.");
		}
	}
	
	//모든 probono 정보 반환
	public static ArrayList<ProbonoDTO> getAllProbonos() throws SQLException{
		return ProbonoDAO.getAllProbonos();
	}
	//probono id로 검색
	public static ProbonoDTO getProbono(String probonoId) throws SQLException, NotExistException{
		ProbonoDTO probono = ProbonoDAO.getProbono(probonoId);
		if(probono == null){
			throw new NotExistException("검색하신 재능기부 정보가 없습니다.");
		}
		return probono;
	}
	//새로운 probono 저장
	public static boolean addProbono(ProbonoDTO probono) throws SQLException{
		return ProbonoDAO.addProbono(probono);
	}
	//기존 probono 수정
	public static boolean updateProbono(String probonoId, String probonoPurpose) throws SQLException, NotExistException{
		notExistProbono(probonoId);
		return ProbonoDAO.updateProbono(probonoId, probonoPurpose);
	}
	//probono 삭제
	public boolean deleteProbono(String probonoId) throws SQLException, NotExistException{
		notExistProbono(probonoId);
		return ProbonoDAO.deleteProbono(probonoId);
	}
		
	
	//------------------------------------------------------------------------------------------------
	//Activist - CRUD
	public static void addActivist(EntityManager em, ActivistDTO activist) throws MessageException{
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(activist);
			tx.commit();
			log.warn(activist.getActivistId() + "생성 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"이미 존재하는 ID입니다 다시 시도 하세요");
		}
	}
	
	public static void updateActivist(EntityManager em, String activistId, String major) throws MessageException {		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			ActivistDTO activist = em.find(ActivistDTO.class, activistId);
			activist.setMajor(major);
			tx.commit();
			log.warn(activistId + " 업데이트 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +" 업데이트 오류입니다.");
		}
	}
	
	public static void deleteActivist(EntityManager em, String activistId) throws SQLException, NotExistException, MessageException{
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try { 
			ActivistDTO activist = em.find(ActivistDTO.class, activistId);
			em.remove(activist);
			tx.commit();
			log.warn(activistId + " 삭제 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"삭제 오류입니다.");
		}
	}
	
	public static ActivistDTO getActivist(EntityManager em, String activistId) throws SQLException, NotExistException{
		ActivistDTO activist = em.find(ActivistDTO.class, activistId);
		if(activist == null){
			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
		}
		log.warn(activistId + " 출력 기록");
		return activist;
	}
	
	public static ArrayList<ActivistDTO> getAllActivists(EntityManager em) throws SQLException, NotExistException{
		List<ActivistDTO> list = new JpaResultMapper().list(em.createNativeQuery("SELECT * FROM activist"), ActivistDTO.class);
		if(list.size() == 0){
			throw new NotExistException("현재 재능 기부자가 존재하지 않습니다.");
		}
		log.warn("activist list 출력 기록");
		return (ArrayList<ActivistDTO>) list;
	}
	
	//Recipient - CRUD
	public static void addRecipient(EntityManager em, RecipientDTO recipient) throws SQLException, MessageException{
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(recipient);
			tx.commit();
			log.warn("recipient 생성 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"이미 존재하는 ID입니다 다시 시도 하세요");
		}
	}
	
	public static void updateRecipient(EntityManager em, String recipientId, String receiveContent) throws SQLException, NotExistException, MessageException{
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			RecipientDTO recipient = em.find(RecipientDTO.class, recipientId);
			recipient.setReceiveContent(receiveContent);
			tx.commit();
			log.warn("업데이트 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"업데이트 오류입니다.");
		}
	}
	
	public static void deleteRecipient(EntityManager em, String recipientId) throws SQLException, NotExistException, MessageException{
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try { 
			RecipientDTO recipient = em.find(RecipientDTO.class, recipientId);
			em.remove(recipient);
			tx.commit();
			log.warn(recipientId + "삭제 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"삭제 오류입니다.");
		}
	}
	
	public static RecipientDTO getRecipient(EntityManager em, String recipientId) throws SQLException, NotExistException{
		RecipientDTO recipient = em.find(RecipientDTO.class, recipientId);
		if(recipient == null){
			throw new NotExistException("검색하는 재능 수혜자가 미 존재합니다.");
		}
		log.warn(recipientId + " 출력 기록");
		return recipient;
	}
	
	public static ArrayList<RecipientDTO> getAllRecipients(EntityManager em) throws SQLException, NotExistException{
		List<RecipientDTO> list = new JpaResultMapper().list(em.createNativeQuery("SELECT * FROM recipient"), RecipientDTO.class);
		if(list.size() == 0){
			throw new NotExistException("현재 재능 기부자가 존재하지 않습니다.");
		}
		log.warn("recipient list 출력 기록");
		return (ArrayList<RecipientDTO>) list;
	}
	
	//ProjectProjectDAO - CRUD
	public static void notExistProbonoProject(int probonoProjectId) throws NotExistException, SQLException{
		ProbonoProjectDTO probonoProject = ProbonoProjectDAO.getProbonoProject(probonoProjectId);
		if(probonoProject == null){
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
	}
	
	//새로운 Probono Project 등록
	public static boolean addProbonoProject(ProbonoProjectDTO probonoProject) throws SQLException{
		return ProbonoProjectDAO.addProbonoProject(probonoProject);
	}
	//존재하는 Probono Project의 재능 기부자 수정
	public static boolean updateProbonoProjectActivist(int probonoProjectId, String activistId) throws SQLException, NotExistException{
		notExistProbonoProject(probonoProjectId);
		return ProbonoProjectDAO.updateProbonoProjectActivist(probonoProjectId, activistId);
	}
	
	//존재하는 Probono Project의 재능 수해자 수정
	public static boolean updateProbonoProjectReceive(int probonoProjectId, String  receiveId) throws SQLException, NotExistException{
		notExistProbonoProject(probonoProjectId);
		return ProbonoProjectDAO.updateProbonoProjectReceive(probonoProjectId, receiveId);
	}
	
	//존재하는 Probono Project 삭제
	public static boolean deleteProbonoProject(int probonoProjectId) throws SQLException, NotExistException{
		notExistProbonoProject(probonoProjectId);
		return ProbonoProjectDAO.deleteProbonoProject(probonoProjectId);
	}
	
	//특정 Probono Project 검색 
	public static ProbonoProjectDTO getProbonoProject(int probonoProjectId) throws SQLException, NotExistException{
		ProbonoProjectDTO probonoProject = ProbonoProjectDAO.getProbonoProject(probonoProjectId);
		if(probonoProject == null){
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
		return probonoProject;
	}
	
	//모든 Probono Project 검색 
	public static ArrayList<ProbonoProjectDTO> getAllProbonoProjects(EntityManager em) throws SQLException,NotExistException{
		log.warn("ProbonoProject list 출력 기록");
		return ProbonoProjectDAO.getAllProbonoProjects(em);
	}
}
