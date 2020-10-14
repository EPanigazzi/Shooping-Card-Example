package web;

import java.io.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Creamos o recuperamos el objeto HttpSession
        HttpSession session = request.getSession();

        //Recuperamos la lista de articulos previos, si es que existen
        List<String> articulos = (List<String>) session.getAttribute("articulos");

        //Verficamos si la lista de articulos existe
        if (articulos == null) {
            //Inicializamos la lista de articulos
            articulos = new ArrayList<>();
            session.setAttribute("articulos", articulos);
        }

        //Procesamos el nuevo articulo
        String articuloNuevo = request.getParameter("articulo");

        //Revisamos y agregamos el articulo nuevo
        if (articuloNuevo != null && !articuloNuevo.trim().equals("")) {
            articulos.add(articuloNuevo);
        }

        try (
            //Imprimir lista de articulos
            PrintWriter out = response.getWriter()) {
            out.print("<h1>Lista de articulos</h1>");
            out.print("<br />");
            out.print("<ul>");
            for (String articulo : articulos) {
                out.print("<li>" + articulo + "</li>");
            }
            out.print("</ul>");

            //Agregaos un link para volver al inicio
            out.print("<br/>");
            out.print("<a href='/EjemploCarritoCompras'>Volver al inicio</a>");
        }
    }
}
