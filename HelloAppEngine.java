package myPack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Messages;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {
	
	Connection conn; // Cloud SQL connection
	// Cloud SQL table creation commands		
	final String createMessageTableSql =
	    "CREATE TABLE IF NOT EXISTS messages (name VARCHAR(255), message TEXT,  id INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(id))";

	@Override
	public void init() throws ServletException {
	  try {
		
		  try {
			Class.forName("com.mysql.jdbc.GoogleDriver");
			} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			} 
		  
	     String url = "jdbc:google:mysql://primeval-falcon-290208:us-central1:mysqldatabaseserver1/visitorcomment";

	     try {
	      conn = DriverManager.getConnection(url, "root", "0304");

	      // Create the tables so that the SELECT query doesn't throw an exception
	      // if the user visits the page before any posts have been added
	      conn.createStatement().executeUpdate(createMessageTableSql); 
	      } catch (SQLException e) {
	      throw new ServletException("Unable to connect to SQL server", e);
	      }
	     
	  } finally {
	    // Nothing really to do here.
	  }
	}

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    
    //*********************get messages already saved in the database
    
    // Our SQL SELECT query. 
    String query = "SELECT * FROM messages"; 
    // create the java statement from java.sql
    Statement st;
	try {
		st = conn.createStatement();
  
    // execute the query, and get a java resultset
    ResultSet rs = st.executeQuery(query);
    
    // temporary list of messages from java util
    List<Messages> lm = new ArrayList<Messages>();
    
    // iterate through the java resultset
    while (rs.next())
    {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      String textmessage = rs.getString("message");
     
      Messages m = new Messages(id, name, textmessage);
      
      //add the m object to the lm temporary list
      lm.add(m);
  
    }
    st.close();
    
	//now prepare the list of messages to the jsp page
	request.setAttribute("listofmessages", lm);
	
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//forward the set of list of messages to the form.jsp page for display or view
			try {
				request.getRequestDispatcher("/form.jsp").forward(request, response);
				} catch (ServletException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
	


  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {        
		
	
		//*********Save in the database the new message typed by the user
	  
		//Process the fields of the form
		String name = request.getParameter("username");
		String textmessage = request.getParameter("usermessage");
	
		// the mysql insert statement
	      String query = "INSERT INTO messages (name, message)"
	        + " values (?,?)";

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
		
	      preparedStmt.setString (1, name);
	      preparedStmt.setString (2, textmessage);
	      

	      // execute the preparedstatement which means add the message to database
	      preparedStmt.executeUpdate();
	      }catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			// go to the do get method of the hello servlet
			response.sendRedirect("/hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}