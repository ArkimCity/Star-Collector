package probono.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import probono.model.LoginService;
import probono.model.WordApi;
import probono.model.WorldOfWordsCRUDService;
import probono.model.dto.UserEntity;
import probono.model.dto.UserWordEntity;
import probono.model.util.PublicCommon;

@Slf4j

@WebServlet("/worldofwords")
public class WorldOfWordsController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//선생님 코멘트 - 컨트롤러의 현재 엔티티매니저는 컨트롤러에서 사용하지 않는 것이 좋다. 추후 프로젝트 진행시에는  컨트롤러가 아니라 서비스에서 사용해야함
		String command = request.getParameter("command");
		try{
			if(command.equals("login")){
				login(request, response);
			}else if(command.equals("logout")){
				logout(request, response);
			}else if(command.equals("userInsert")){
				userInsert(request, response);
			}else if(command.equals("userCommunityInsert")){
				communityInsert(request, response);
			}else if(command.equals("userCommunityAll")){
				communityAll(request, response);
			}else if(command.equals("saveUserWord")){//단어리스트에 단어 보내주는 역할
				saveUserWord(request, response); 
			}else if(command.equals("deleteUserWord")){//단어리스트에 단어 보내주는 역할
				deleteUserWord(request, response); 
			}else if(command.equals("getWordList")){//단어리스트에 단어 보내주는 역할
				getWordList(request, response); 
			}else {
				request.setAttribute("errorMsg", "잘못된 명령입니다. 다시 시도해 주십시오");
				request.getRequestDispatcher("showError.jsp").forward(request, response);
			}
			
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}
	}

	private void saveUserWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String id = (String) request.getSession().getAttribute("id");
		String word = request.getParameter("word");

		if (id == null || id.length() == 0 || word == null) {
			request.setAttribute("errorMsg", "정보가 부족합니다");
		} else {
			try {
				WorldOfWordsCRUDService.saveUserWord(id, word);
				getUserWords(request, response);
				url = "wordList.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
		}
		request.getRequestDispatcher(url).forward(request, response); 
	}

	private void deleteUserWord(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String url = "showError.jsp";
		String id = (String) request.getSession().getAttribute("id");
		String word = request.getParameter("word");
		try {
			WorldOfWordsCRUDService.deleteUserWord(id, word);
			getUserWords(request, response);
			url = "wordList.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void getUserWords(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> userwords = new ArrayList<String>();
		if (request.getSession().getAttribute("id") == null) {
			request.setAttribute("noWordsMsg", "아직 저장하신 단어가 없네요!");
		} else {
			try {
				String id = (String)request.getSession().getAttribute("id");
				userwords = WorldOfWordsCRUDService.getUserWords(id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.setAttribute("userwords", userwords);
	}

	private void userInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		String name = request.getParameter("userName");
		String nickname = request.getParameter("nickname");

		if (id == null || id.length() == 0 || name == null) {
			request.setAttribute("errorMsg", "정보가 부족합니다");
		} else {
			UserEntity user = UserEntity.builder().userId(id).password(pw).userName(name).nickname(nickname).build();
			try {
				WorldOfWordsCRUDService.addUser(user);
				request.getSession().setAttribute("id", id);
				request.setAttribute("successMsg", "가입 완료");
				url = "activistDetail.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	private void communityInsert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void communityAll(HttpServletRequest request, HttpServletResponse response) {

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.getSession().setAttribute("id", null);
			url = "wordList.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "login-page.jsp";
		try {
			if (LoginService.login(request, response)) {
				getUserWords(request, response);
				url = "wordList.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			url = "showError.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	public void getWordList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<HashMap<String, String>> words = new ArrayList<HashMap<String, String>>();
		String inputnumber = request.getParameter("inputnumber");
		String url = "showError.jsp";
		if (inputnumber == null) {
			words = WordApi.crawler("10");
			url = "wordList.jsp";
		} else {
			words = WordApi.crawler(inputnumber);
			url = "wordList.jsp";
		}
		getUserWords(request, response);
		request.setAttribute("words", words);
		request.getRequestDispatcher(url).forward(request, response);
	}
	/*
	 * public void activist(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String url =
	 * "showError.jsp"; try { request.setAttribute("activist",
	 * WorldOfWordsCRUDService.get(em, request.getParameter("activistId"))); url =
	 * "activistDetail.jsp"; }catch(Exception s){ request.setAttribute("errorMsg",
	 * s.getMessage()); s.printStackTrace(); }
	 * request.getRequestDispatcher(url).forward(request, response); }
	 * 
	 * public void recipient(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String url =
	 * "showError.jsp"; try { request.setAttribute("recipient",
	 * ProbonoService.getRecipient(em, request.getParameter("recipientId"))); url =
	 * "recipientDetail.jsp"; }catch(Exception s){ request.setAttribute("errorMsg",
	 * s.getMessage()); s.printStackTrace(); }
	 * request.getRequestDispatcher(url).forward(request, response); }
	 * 
	 * public void activistUpdateReq(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String url =
	 * "showError.jsp"; try { request.setAttribute("activist",
	 * ProbonoService.getActivist(em, request.getParameter("activistId"))); url =
	 * "activistUpdate.jsp"; }catch(Exception s){ request.setAttribute("errorMsg",
	 * s.getMessage()); s.printStackTrace(); }
	 * request.getRequestDispatcher(url).forward(request, response); }
	 * 
	 * public void activistUpdate(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { try{
	 * ProbonoService.updateActivist(em, request.getParameter("activistId"),
	 * request.getParameter("major")); request.setAttribute("successMsg", "수정 완료");
	 * activist(em, request, response); }catch(Exception s){
	 * request.setAttribute("errorMsg", s.getMessage());
	 * request.getRequestDispatcher("showError.jsp").forward(request, response); } }
	 * 
	 * public void activistDelete(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String url =
	 * "showError.jsp"; try{ String id = request.getParameter("activistId");
	 * ProbonoService.deleteActivist(em, id); url= "probono?command=activistAll";
	 * }catch(Exception s){ request.setAttribute("errorMsg", s.getMessage()); }
	 * request.getRequestDispatcher(url).forward(request, response); }
	 * 
	 * public void recipientUpdateReq(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { String
	 * url = "showError.jsp"; try { request.setAttribute("recipient",
	 * ProbonoService.getRecipient(em, request.getParameter("recipientId"))); url =
	 * "recipientUpdate.jsp"; }catch(Exception s){ request.setAttribute("errorMsg",
	 * s.getMessage()); s.printStackTrace(); }
	 * request.getRequestDispatcher(url).forward(request, response); }
	 * 
	 * public void recipientUpdate(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { try{
	 * ProbonoService.updateRecipient(em, request.getParameter("recipientId"),
	 * request.getParameter("receiveContent")); request.setAttribute("successMsg",
	 * "수정 완료"); recipient(em, request, response); }catch(Exception s){
	 * request.setAttribute("errorMsg", s.getMessage());
	 * request.getRequestDispatcher("showError.jsp").forward(request, response); } }
	 * 
	 * public void recipientDelete(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String url =
	 * "showError.jsp"; try{ String id = request.getParameter("recipientId");
	 * ProbonoService.deleteRecipient(em, id); request.setAttribute("successMsg",
	 * "삭제 완료"); }catch(Exception s){ request.setAttribute("errorMsg",
	 * s.getMessage()); } request.getRequestDispatcher(url).forward(request,
	 * response); }
	 */
}
