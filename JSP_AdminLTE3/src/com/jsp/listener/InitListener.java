package com.jsp.listener;

import java.lang.reflect.Method;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.BoardDAO;
import com.jsp.dao.MemberDAO;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberServiceImpl;

@WebListener
public class InitListener implements ServletContextListener {

    public InitListener() {
    }

	public void contextDestroyed(ServletContextEvent ctxEvent)  { 
     
    }

    public void contextInitialized(ServletContextEvent ctxEvent)  { 
    	//sql세션을 dao에 넣고 dao를 서비스에 넣어줘야 끝나
    	String sqlSessionFactoryType = ctxEvent.getServletContext().getInitParameter("sqlSessionFactory");
    	String memberDAOType = ctxEvent.getServletContext().getInitParameter("memberDAO");
    	String boardDAOType = ctxEvent.getServletContext().getInitParameter("boardDAO");
    	
    	try {
    		
    	SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)Class.forName(sqlSessionFactoryType).newInstance();
    	
    	Class<?> cls = Class.forName(memberDAOType);
    	
    	Method setSqlSessionFactory = cls.getMethod("setSessionFactory", SqlSessionFactory.class);
   
    	Object obj = cls.newInstance();
    	setSqlSessionFactory.invoke(obj, sqlSessionFactory);
    	
    	MemberDAO memberDAO = (MemberDAO)obj;
    	    	
    	MemberServiceImpl.getInstance().setMemberDAO(memberDAO);
    	

    	Class<?> cls2 = Class.forName(boardDAOType);
    	
    	setSqlSessionFactory = cls2.getMethod("setSessionFactory", SqlSessionFactory.class);
     	
    	Object obj2 = cls2.newInstance();
    	setSqlSessionFactory.invoke(obj2, sqlSessionFactory);
    	
    	BoardDAO boardDAO= (BoardDAO)obj2;
    	
    	BoardServiceImpl.getInstance().setBoardDAO(boardDAO);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	System.out.println("리스너 발동");
    	
	}	
}
