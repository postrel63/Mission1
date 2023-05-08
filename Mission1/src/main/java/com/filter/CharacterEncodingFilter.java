package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("*.jsp")
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
                    throws IOException, ServletException {


        //before 실행 후 다음필터나 다음 서블릿으로 실행하고
        //결과가 와서 응답하면
//        System.out.println("before filter, 서블릿이 실행하기 전에 이뤄지는 설정들 ");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");

        filterChain.doFilter(servletRequest,servletResponse);
//        System.out.println("after filter 서블릿이 실행되고 응답된 뒤에 이뤄지는 설정들 ");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
