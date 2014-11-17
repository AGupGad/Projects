package person;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Servlet implementation class PersonSearch
 */
@WebServlet("/PersonSearch")
public class PersonSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoClient mongo = null;
	ServletConfig config=null;  
	  private static String firstname;
	    private static String lastname;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonSearch() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config)
    {
        try {
        	this.config = config;
        	mongo	= new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MongoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			firstname = (String) request.getAttribute("firstname_search");
			lastname = (String) request.getAttribute("lastname_search");
			DBCollection personCollection;	
			DB db = mongo.getDB("test");
			personCollection = db.getCollection("mongodbtest");
			if(personCollection == null){
				
			}
			 DBCursor cursor = null;
			 try{
				BasicDBObject andQuery = new BasicDBObject();
			    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			    obj.add(new BasicDBObject("firstname_search",firstname));
			    obj.add(new BasicDBObject("lastname_search", lastname));
			    andQuery.put("$and", obj);	
			    int count;
			    cursor = personCollection.find(andQuery);
			    count = cursor.count();
			    if(count >1){
			    	request.setAttribute("count", count);
			    	request.getRequestDispatcher("/SearchDetailDisplay.jsp").forward(request, response);
			     }
			    else
			    	request.setAttribute("multipleRecords",false);
			    request.getRequestDispatcher("/SearchDetailDisplay.jsp").forward(request, response);
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}catch(Exception e){
			
		}
	}

}
