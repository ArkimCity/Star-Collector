package probono.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import probono.model.ProbonoService;
import probono.model.dto.ActivistDTO;
import probono.model.util.PublicCommon;

@Slf4j

@WebServlet("/probono")
public class ProbonoFrontController extends HttpServlet {
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = PublicCommon.getEntityManger();
		
		request.setCharacterEncoding("utf-8");
		//선생님 코멘트 - 컨트롤러의 현재 엔티티매니저는 컨트롤러에서 사용하지 않는 것이 좋다. 추후 프로젝트 진행시에는  컨트롤러가 아니라 서비스에서 사용해야함
		String command = request.getParameter("command");
		try{
			if(command.equals("probonoProjectAll")){//모든 probono project 정보 검색
				probonoProjectAll(em, request, response);
			}else if(command.equals("activistAll")){//모든 재능 기부자 검색
				activistAll(em, request, response);
			}else if(command.equals("activist")){//특정 재능 기부자 정보 검색
				activist(em, request, response);
			}else if(command.equals("activistInsert")){//재능 기부자 추가 등록
				activistInsert(em, request, response);
			}else if(command.equals("activistUpdateReq")){//재능 기부자 정보 수정요청
				activistUpdateReq(em, request, response);
			}else if(command.equals("activistUpdate")){//재능 기부자 정보 수정
				activistUpdate(em, request, response);
			}else if(command.equals("activistDelete")){//재능 기부자 탈퇴[삭제]
				activistDelete(em, request, response);
			}else if(command.equals("recipient")){//특정 재능 기부자 정보 검색
				recipient(em, request, response);
			}else if(command.equals("recipientUpdateReq")){//재능 기부자 정보 수정요청
				recipientUpdateReq(em, request, response);
			}else if(command.equals("recipientUpdate")){//재능 기부자 정보 수정
				recipientUpdate(em, request, response);
			}else if(command.equals("recipientDelete")){//재능 기부자 탈퇴[삭제]
				recipientDelete(em, request, response);
			}else {
				request.setAttribute("errorMsg", "잘못된 명령입니다. 다시 시도해 주십시오");
				request.getRequestDispatcher("showError.jsp").forward(request, response);
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}finally {
			em.close();
			log.warn("em 종료 기록");
		}
	}

	//모두 ProbonoProject 검색 메소드
	public void probonoProjectAll(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("probonoProjectAll", ProbonoService.getAllProbonoProjects(em));
			url = "probonoProjectList.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void activistAll(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("activistAll", ProbonoService.getAllActivists(em));
			url = "activistList.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void activist(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("activist", ProbonoService.getActivist(em, request.getParameter("activistId")));
			url = "activistDetail.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void recipient(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("recipient", ProbonoService.getRecipient(em, request.getParameter("recipientId")));
			url = "recipientDetail.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void activistInsert(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String id = request.getParameter("activistId");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String major = request.getParameter("major");
		
		if(id == null || id.length() == 0 || name == null) {
			request.setAttribute("errorMsg", "정보가 부족합니다");
		} else {
			ActivistDTO activist = ActivistDTO.builder().activistId(id).name(name).password(pw).major(major).build();
			try{
				ProbonoService.addActivist(em, activist);
				request.setAttribute("activist", activist);
				request.setAttribute("successMsg", "가입 완료");
				url = "activistDetail.jsp";
			}catch(Exception s){
				request.setAttribute("errorMsg", s.getMessage());
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void activistUpdateReq(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("activist", ProbonoService.getActivist(em, request.getParameter("activistId")));
			url = "activistUpdate.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void activistUpdate(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			ProbonoService.updateActivist(em, request.getParameter("activistId"), request.getParameter("major"));
			request.setAttribute("successMsg", "수정 완료");
			activist(em, request, response);
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
		}
	}
	
	public void activistDelete(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try{
			String id = request.getParameter("activistId");
			ProbonoService.deleteActivist(em, id);
			url= "probono?command=activistAll";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage()); 
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void recipientUpdateReq(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("recipient", ProbonoService.getRecipient(em, request.getParameter("recipientId")));
			url = "recipientUpdate.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void recipientUpdate(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			ProbonoService.updateRecipient(em, request.getParameter("recipientId"), request.getParameter("receiveContent"));
			request.setAttribute("successMsg", "수정 완료");
			recipient(em, request, response);
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
		}
	}
	
	public void recipientDelete(EntityManager em, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try{
			String id = request.getParameter("recipientId");
			ProbonoService.deleteRecipient(em, id);
			request.setAttribute("successMsg", "삭제 완료");
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
