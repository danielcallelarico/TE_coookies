
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

   public int contador=0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensaje=null;
        String msj;
        boolean nuevaVisita=true;
        
        //obtener el el arreglo de cookies
        Cookie[] cookies = request.getCookies();
        
        if(cookies != null){
            for (Cookie c: cookies){
                if((c.getName().equals("visitante")) && c.getValue().equals("SI")){
                    nuevaVisita=false;
                    contador=contador+1;
                    break;
                }
            }
        }
        if(nuevaVisita){
            Cookie ck = new Cookie("visitante","SI");
            ck.setMaxAge(120);
            ck.setComment("Control de nuevos visitantes");
            response.addCookie(ck);
            contador=1;
            mensaje = "Gracias por visitar nuestra pagina"; 
            msj = "Visitas A la Pagina: "+contador;
        }
        else{
            mensaje="Estamos agredecidos por tenerlo nuevamente";
            msj = "Visitas A la Pagina: "+contador;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>"+mensaje+"</h1>");
        out.println("<h1>"+msj+"</h1");
         out.println("<br>");
        out.println("<a href='index.jsp'>Ir al inicio</a>");
    }
}
