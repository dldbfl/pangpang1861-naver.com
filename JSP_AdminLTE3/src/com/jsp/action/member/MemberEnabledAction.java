package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.MemberServiceImpl;

public class MemberEnabledAction implements Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/enabled_success";

		String id = request.getParameter("id");

		try {
			MemberServiceImpl.getInstance().enabled(id);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "member/enabled_fail";
		}
		return url;
	}

}









