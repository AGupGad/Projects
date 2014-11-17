package person;
import com.mongodb.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class PersonSave
 */
@WebServlet("/PersonSave")
public class PersonSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String UPLOAD_PATH= "C:\\mongodb\\bin\\data\\db\\FileUploads";
    private static String firstname;
    private static String lastname;
	private static String filePath;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	MongoClient mongo = null;
	ServletConfig config=null;
	public PersonSave() {
        super();
        // TODO Auto-generated constructor stub
    }
public void init(ServletConfig config)
    {
        try {
        	this.config=config;
        	mongo	= new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MongoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
			System.out.println("in do post");
			DBCollection personCollection;	
			DB db = mongo.getDB("test");
			personCollection = db.getCollection("mongodbtest");
			if(personCollection == null){
				personCollection = db.createCollection("mongodbtest", null);
			}
			Person person = new Person();
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for(FileItem item: items){
				if(item.isFormField()){
					String fieldname = item.getFieldName();
					String fieldvalue = item.getString();
					if(fieldname.equalsIgnoreCase("firstname")){
						firstname = fieldvalue;
						person.setFirstName(fieldvalue);
					}
					if(fieldname.equalsIgnoreCase("lastname")){
						lastname = fieldvalue;
						person.setLastName(fieldvalue);
					}
					
				}
				else{			
						for(FileItem fileItem: items){
								if(!fileItem.isFormField()){
									filePath = fileItem.getName();
									if(filePath.contains("//") || filePath.contains("\\")){
										String fileName = filePath.substring(filePath.lastIndexOf("\\"));
										System.out.println(fileName);
										fileItem.write(new File(UPLOAD_PATH+ fileName));
									}else{
									String fileName = filePath;
									System.out.println(fileName);
									String remoteFileUploadPath = UPLOAD_PATH+ "\\" + fileName;
									fileItem.write(new File(remoteFileUploadPath ));
									
								}
																	
								}
						}
					
					}
				}
					createPerson(person, filePath,  personCollection);
					response.setContentType("text/html");
					request.setAttribute("firstname", firstname);
					request.setAttribute("lastname", lastname);
					request.getRequestDispatcher("/AddConfirmation.jsp").forward(request, response);
				}
				catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
		
		}
		private static DBObject createDBObject(Person p,String filePath, DBCollection c) {
			 BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();  
			try{
				 
				  docBuilder.append("age", p.getAge());
			      docBuilder.append("firstname", p.getFirstName());
			      docBuilder.append("lastname", p.getLastName());
			      docBuilder.append("haircolor", p.getHairColor());
			      docBuilder.append("filepath", filePath);
			      
			  }catch(Exception e){
				  System.out.println(e.toString());
			  }
			  return docBuilder.get();   
		}
		private static void  createPerson(Person person, String filePath, DBCollection coll) {
				try{
					DBObject doc = PersonSave.createDBObject(person, filePath , coll);
					coll.insert(doc);
					doc.put("documentPath", filePath);
					}catch(Exception im){
					System.out.println(im.toString());
				}
			}
		private static DBCursor  queryPerson(DBCollection coll,String firstname, String lastname) {
			 DBCursor cursor = null;
			 try{
				BasicDBObject andQuery = new BasicDBObject();
			    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			    obj.add(new BasicDBObject("firstname",firstname));
			    obj.add(new BasicDBObject("lastname", lastname));
			    andQuery.put("$and", obj);			   
			    cursor = coll.find(andQuery);
			}catch(Exception e){
				System.out.println(e.toString());
			}
		    return cursor;
			}
    

}





