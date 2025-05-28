package com.lib.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import com.lib.dao.UserDao;
import com.lib.entities.User;
import com.lib.helper.Connectionprovider;
import com.lib.helper.EmailUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String role = request.getParameter("role").trim();
        String password = request.getParameter("password").trim();

     

        UserDao userDao = new UserDao(Connectionprovider.getConnection());

  
        if (userDao.isEmailExists(email)) {
        	response.getWriter().println("mail Already exists");   
        	return;
        }

      
        String membershipNumber = ""+(int)(Math.random() * 10000);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password);
        user.setMembershipNumber(membershipNumber);

        boolean success = userDao.registerUser(user);

        if (success) {

            String subject = "Library Registration Successful";
            String body = "Dear " + name + ",\n\n"
                    + "You have been successfully registered.\n"
                    + "Membership Number: " + membershipNumber + "\n"
                    + "Password: " + password + "\n\n"
                    + "Regards,\nLibrary System";

            EmailUtil.sendEmail(email, subject, body);

            request.setAttribute("success", "Registration successful. Membership No: " + membershipNumber);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
        	 response.getWriter().println("mail Already exists");   
        }
    }
}
