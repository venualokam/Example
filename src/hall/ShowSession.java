package hall;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.StringWriter;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: alokamve
 * Date: 5/9/12
 * Time: 6:45 PM

 */
public class ShowSession extends javax.servlet.http.HttpServlet {

   Logger log = LoggerFactory.getLogger(ShowSession.class);
    public void doGet(HttpServletRequest request,
                        HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String heading;
        Integer accessCount = 0;
        if (session.isNew()) {
          heading = "Welcome, Newcomer";
        } else {
          heading = "Welcome Back";
          Integer oldAccessCount =
            // Use getAttribute, not getValue, in version
            // 2.2 of servlet API.
            (Integer)session.getValue("accessCount");
          if (oldAccessCount != null) {
            accessCount =
                    oldAccessCount + 1;
          }
        }

        List  l=new ArrayList();
        try{
            l=null;
           l.size();
        }catch(Exception e){
            log.error("#### -- STACK TRACE -- ####",e);

        }
        // Use putAttribute in version 2.2 of servlet API.
        session.putValue("accessCount", accessCount);

        out.println(
                    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                    "<H1 ALIGN=\"CENTER\">" + heading + "</H1>\n" +
                    "<H2>Information on Your Session:</H2>\n" +
                    "<TABLE BORDER=1 ALIGN=CENTER>\n" +
                    "<TR BGCOLOR=\"#FFAD00\">\n" +
                    "  <TH>Info Type<TH>Value\n" +
                    "<TR>\n" +
                    "  <TD>ID\n" +
                    "  <TD>" + session.getId() + "\n" +
                    "<TR>\n" +
                    "  <TD>Creation Time\n" +
                    "  <TD>" + new Date(session.getCreationTime()) + "\n" +
                    "<TR>\n" +
                    "  <TD>Time of Last Access\n" +
                    "  <TD>" + new Date(session.getLastAccessedTime()) + "\n" +
                    "<TR>\n" +
                    "  <TD>Number of Previous Accesses\n" +
                    "  <TD>" + accessCount + "\n" +
                    "</TABLE>\n" +
                    "</BODY></HTML>");
      }

      public void doPost(HttpServletRequest request,
                         HttpServletResponse response)
          throws ServletException, IOException {
        doGet(request, response);
      }

    public void service(HttpServletRequest request,HttpServletResponse response)   throws ServletException, IOException{
                              log.debug("Do service-----3333333333 666 777----------");
         doGet(request, response);
    }
     public void destroy()  {

       log.debug("Do destroy------22222222222222----777-----");
     }

}
