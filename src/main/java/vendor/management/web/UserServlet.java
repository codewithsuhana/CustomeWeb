package vendor.management.web;

import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vendor.management.dao.UserDAO;
import vendor.management.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
      
    public void init() {
       this.userDAO = new UserDAO();
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            if ("/list".equals(action)) {
                listUser(request, response);
            } else if ("/update".equals(action)) {
                updateUser(request, response);
            } else if ("/new".equals(action)) {
                showNewForm(request, response);
            } else if ("/edit".equals(action)) {
                showEditForm(request, response);
            } else if ("/delete".equals(action)) {
                deleteUser(request, response);
            } else if ("/insert".equals(action)) {
                insertUser(request, response);
            } else {
                // Handle unknown actions or redirect to a default page
                response.sendRedirect(request.getContextPath() + "/list");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        // Get user input
        String email = request.getParameter("email");
        String rawPassword = request.getParameter("password"); // Assuming you have a password field in your form

        // Retrieve users from the database for the given email
        List<User> users = userDAO.selectUserByEmail(email);

        if (users != null && !users.isEmpty()) {
            // Access the first user in the list
            User user = users.get(0);

            if (BCrypt.checkpw(rawPassword, user.getPassword())) {
                // Passwords match, login successful
                // Add user to session or perform any other actions
                response.sendRedirect("dashboard");
                return;
            }
        }

        // Incorrect credentials or no users found, handle accordingly
        response.sendRedirect("login?error=invalidCredentials");
    }

	
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String searchType = request.getParameter("searchType");
        String searchValue = request.getParameter("searchValue");

        List<User> listUser = new ArrayList<>(); // Initialize the list

        if (searchType != null && searchValue != null && !searchValue.isEmpty()) {
            // Perform search based on the selected type
            switch (searchType) {
                case "fname":
                    listUser = userDAO.selectUserByFname(searchValue);
                    break;
                case "city":
                    listUser = userDAO.selectUserByCity(searchValue);
                    break;
                case "email":
                    List<User> usersByEmail = userDAO.selectUserByEmail(searchValue);
                    if (usersByEmail != null && !usersByEmail.isEmpty()) {
                        listUser.addAll(usersByEmail);
                    }
                    break;
                case "phone":
                    long phone = Long.parseLong(searchValue);
                    List<User> usersByPhone = userDAO.selectUserByPhone(phone);
                    if (usersByPhone != null && !usersByPhone.isEmpty()) {
                        listUser.addAll(usersByPhone);
                    }
                    break;
                default:
                    // Handle default case
                    break;
            }
        } else {
            // If no search criteria provided, retrieve all users
            listUser = userDAO.selectAllUsers();
        }

        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }





    	
	private void updateUser(HttpServletRequest request,HttpServletResponse response) 
				throws  IOException, SQLException{
		    int id = Integer.parseInt(request.getParameter("id"));
			String fname = request.getParameter("fname");
	        String lname = request.getParameter("lname");
	        String street = request.getParameter("street");
	        String address = request.getParameter("address");
	        String city = request.getParameter("city");
	        String state = request.getParameter("state");
	        String email = request.getParameter("email");
	        long phone = Long.parseLong(request.getParameter("phone"));
	        User book = new User(id,fname, lname, street, address, city, state, email, phone);
	        this.userDAO.updateUser(book);
	        response.sendRedirect("list");
		}
		
	private void deleteUser(HttpServletRequest request,HttpServletResponse response) 
				throws  IOException, SQLException{
			int id = Integer.parseInt(request.getParameter("id"));
			this.userDAO.deleteUser(id);
			response.sendRedirect("list");
	}
	private void showEditForm(HttpServletRequest request,HttpServletResponse response) 
			throws SQLException,ServletException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = this.userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException , IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	// ... (Existing code)

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException, SQLException {
	    // Get user input
	    String fname = request.getParameter("fname");
	    String lname = request.getParameter("lname");
	    String street = request.getParameter("street");
	    String address = request.getParameter("address");
	    String city = request.getParameter("city");
	    String state = request.getParameter("state");
	    String email = request.getParameter("email");
	    long phone = Long.parseLong(request.getParameter("phone"));
	    String rawPassword = request.getParameter("password"); // Assuming you have a password field in your form

	    // Hash the password
	    String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

	    // Create User object
	    User newUser = new User(fname, lname, street, address, city, state, email, phone, hashedPassword);

	    // Store the user in the database
	    userDAO.insertUser(newUser);

	    // Redirect or perform any other actions
	    response.sendRedirect("list");
	}
}

	// ... (Existing code)

