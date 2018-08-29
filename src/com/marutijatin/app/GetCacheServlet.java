package com.marutijatin.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.marutijatin.app.dto.CacheDTO;
import com.marutijatin.app.dto.CommonGenericDTO;
import com.marutijatin.app.utils.CacheLoader;
import com.marutijatin.app.utils.CommonConstants;

/**
 * Servlet implementation class GetCacheServlet
 */
@WebServlet("/GetCacheServlet")
public class GetCacheServlet extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    Gson gson = new Gson();
//	    gson.toJson()
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		ArrayList<CommonGenericDTO> cacheList = CacheLoader.getCache(CommonConstants.CACHE_CITY);
		Gson gson = new Gson();
		String json = gson.toJson(cacheList);
		System.out.println("JSON is "+json);
		CacheDTO cacheDTO = new CacheDTO();
		
		cacheDTO.setData(cacheList);
		json = gson.toJson(cacheDTO);
		System.out.println("Now JSON Again "+json);
		System.out.println("Cache List is "+cacheList);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.close();
		doGet(request, response);
	}

}
