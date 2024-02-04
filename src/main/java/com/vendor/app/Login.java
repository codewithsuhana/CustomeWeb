package com.vendor.app;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/log")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Customer", "root", "sjsuhana");
            PreparedStatement pstmt = con
                    .prepareStatement("select * from `customers_login` where `email` = ? and `password`= ?");
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            PrintWriter p = response.getWriter();
            p.println("successful");

            if (rs.next()) {
                session.setAttribute("name", rs.getString("email"));
                dispatcher = request.getRequestDispatcher("user-list.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement your logic for handling GET requests here
        // For example, you might want to redirect users to the login page
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
