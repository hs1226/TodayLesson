package com.todaylesson.csrf;

import java.io.ByteArrayOutputStream; 

import java.io.IOException;

import java.io.OutputStreamWriter;

import java.io.PrintWriter;

 

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletOutputStream;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletResponseWrapper;

 

import org.apache.commons.lang.StringUtils;

import org.springframework.security.web.csrf.CsrfToken;

/**

 * CsrfTokenAdder.java

 * - CSRF Ptotection을 위한 Text/Html "응답"에 히든태그 Csrf Token 삽입

 * 

 * ################ Spring Security CSRF Protection 설정 ##################

 * [CsrfTokenAdder.java, 서블릿필터] ###"응답에 태그 삽입"###

 * - Spring Security4 이상은 CSRF Enabled가 기본설정

 * - HttpServletRequest(일반  JSP요청), XMLHttpRequest(AJAX) 요청이든 응답에 Csrf 히든테그 삽입

 * - CSRF Protection을 위한 Text/Html 응답에 히든태그 Xsrf Token 삽입

 * - AJAX JSON응답(콤보 채우기등)은 히든태그 Csrf Token 삽입할 필요없다.

 * 

 * [security-config.xaml]

 * - 애당초 Spring Security 적용이 필요없는 부분에 대한 정의(/resources, /images 등)

 * - RequestMatcher에 대한 정의(스프링 시큐리티에서 요청에 대해 CSRF를 적용할건지 아닐건지 판단)

 * - AccessDeniedHandler에 대한 정의(403 에러발생시 대응)

 * 

 * [IfpCsrfRequestMatcher.java] ###"요청에 따라 CSRF 적용여부 결정"###

 * - 스프링 시큐리티에서 CSRF를 적용할건지 아닐건지 판단

 * - AJAX CALL, 첫화면, 최측메뉴로딩, 팝업로등 요청은 CSRF 적용하지 않음

 * - (TEXT/HTML을 응답으로 보낸는 부분은 CsrfTokenAdder 필터에서 Csrf 히든태그 삽입함)

 * - 403 Forbidden 에러가 발생하는 부분이 있다면 이곳에서 추가할 것

 * 

 * [IfpAccessDeniedHandler.java]

 * - 403 Forbidden 에러 발생하는 경우 처리하는 핸들러

 * ######################################################################

 */

public class CsrfTokenAdder implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String url = ((HttpServletRequest)request).getRequestURL().toString();

		String responseText = null;

		HtmlResponseWrapper  newResponse = null;

		CsrfToken token = null;

		

		// 필터대상 에서 제외할 목록 (Spring Security 제외한 항목 넣기)

		final String[] EXCLUDE_URL_LIST = {"/webjars", "/favicon.ico", "/filedownload", "/imgUpload", "/CSS", "/JS", "/IMG", "/FONT", "/ckeditor", "/WEB-INF" };

		//final String[] EXCLUDE_URL_LIST = {"/CSS", "/JS", "/IMG", "/FONT"};

		String paramName = null;

		String strToken = null; 

		String tokenStr = null;

		String csrfTokenInput = null;

		String replacedText = null;	

		boolean excludeState = false;
		

		//-------------------------------------

		// 필터 제외 URI SKIP

		//-------------------------------------

		for( String target : EXCLUDE_URL_LIST )

		{

			if( url.indexOf( target ) > -1 )

			{

				excludeState = true;

				break;

			}

		}

 

		if( excludeState )

		{

			chain.doFilter( request, response );

			return;

		}

		//-------------------------------------

		

		//-------------------------------------------------------------

		// AJAX APPLICATION/JSON RESPONSE, NON TEXT/HTML

		// 화면 콤보 초기화 등에서 AJAX CALL 하는 경우

		// 이 경우는 CSRF 태그를 삽입할 필요없다.

		//-------------------------------------------------------------

        if ( ((HttpServletResponse)response).getContentType() != null 

        		&& "XMLHttpRequest".equals(((HttpServletRequest) request).getHeader("X-Requested-With"))

        		&& ((HttpServletResponse)response).getContentType().contains("application/json")) {  

			response.getWriter().write(responseText);

        } 

        //-------------------------------------------------------------

        // 응답이 text/html인 경우 Csrf 히든 태그를 응답 </form> 앞에 삽입한다. 

        // 일반 JSP로 보내는 응답, AJAX TEXT/HTML 응답인 팝업 화면로딩 등

        //-------------------------------------------------------------

        else if ( ((HttpServletResponse)response).getContentType() != null         		

        		&& ((HttpServletResponse)response).getContentType().contains("text/html")) {   

        	

        	newResponse = new HtmlResponseWrapper ((HttpServletResponse) response);	

    		chain.doFilter(request, newResponse);

    		

    		responseText = newResponse.getCaptureAsString();  

			

        	token = (CsrfToken) request.getAttribute("_csrf");

            paramName = token.getParameterName();

            strToken = token.getToken();

			  

			if (token != null) {

				tokenStr = String.format("<input type=\"hidden\" name=\"%s\" id=\"%s\" value=\"%s\" />",

						paramName, paramName, strToken);

			}

			

			// 일반 JSP로 응답을 보내는 경우(HttpServletRequest, TEXT/HTML응답)

			if (!StringUtils.contains(responseText, "_csrf.parameterName")

				&& StringUtils.contains(responseText, "<form>")) {

				 csrfTokenInput = tokenStr + "</form>";

			     replacedText = StringUtils.replace(responseText, "</form>", csrfTokenInput);

			} 

			// 팝업창 로딩(XMLHttpRequest요청,AJAX TEXT/HTML응답)

			else {

				csrfTokenInput = "<form>" + tokenStr + "</form></body>";

				replacedText = StringUtils.replace(responseText, "</body>", csrfTokenInput);

			}	

		        

			response.getWriter().write(replacedText);

		}

		else {	

			chain.doFilter(request, response);

		}

	}

 

	@Override

	public void init(FilterConfig arg0) throws ServletException {

	}

 

	@Override

	public void destroy() {

	}
	
}
