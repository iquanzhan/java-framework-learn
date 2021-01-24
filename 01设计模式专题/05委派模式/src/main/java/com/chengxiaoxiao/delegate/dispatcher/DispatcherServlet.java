package com.chengxiaoxiao.delegate.dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DispatcherServlet
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-24 15:43
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatcher(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        String mid = req.getParameter("mid");
        if ("getMemberById".equalsIgnoreCase(uri)) {
            new MemberController().getMemberById(mid);
        } else if ("getOrderById".equalsIgnoreCase(uri)) {
            new OrderController().getOrderById();
        } else if ("logout".equalsIgnoreCase(uri)) {
            new SystemController().logout();
        } else {
            resp.getWriter().write("404 Not Found");
        }
    }
}
