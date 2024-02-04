package com.vendor.app;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Customer?useSSL=false", "root", "sjsuhana");
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO `customers_login` (`id`,`name`,`email`,`password`) VALUES (?,?,?,?)");

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            int row = pstmt.executeUpdate();
            PrintWriter p = response.getWriter();
            p.println("Registration completed successfully");
            
            dispatcher=request.getRequestDispatcher("signup.jsp");
            if(row >0) {
            	request.setAttribute("status", "success");
            	
            }else {
            	request.setAttribute("status", "failed");
            }

            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
}

