package in.laksys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

public class Process extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		out.println("<html><body bgcolor='gold'>");
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/crud");
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareCall("insert into candidate (cname, fname, gender, mobile, address) values (?,?,?,?,?)");
			ps.setString(1, req.getParameter("cname"));
			ps.setString(2, req.getParameter("fname"));
			ps.setString(3, req.getParameter("gender"));
			ps.setString(4, req.getParameter("mobile"));
			ps.setString(5, req.getParameter("address"));
			ps.execute();
			
			out.println("<h2>Added successfully!</h2>");
			
			out.println("<a href='candidate.html'>Add more</a>");
			out.println("<a href='index.html'>Home</a>");
			
			conn.close();
		} catch (SQLException exp) {
			out.println("<h2>SQL Exception fired</h2>");
			out.println("<h2 style=color:red>" + exp.getMessage() + "</h2>");
		} catch (NamingException exp) {
			out.println("Naming Exception fired</h2>");
			out.println("<h2 style=color:red>" + exp.getMessage() + "</h2>");
		}
		out.println("</body></html>");
	}
}
