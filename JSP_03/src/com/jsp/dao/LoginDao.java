package com.jsp.dao;

import java.util.List;

import com.jsp.vo.MemberVO;

public interface LoginDao {
	
	public List<MemberVO> loginMember(MemberVO mv);

}
