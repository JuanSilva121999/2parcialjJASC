package com.emergentes.controlador;
import com.emergentes.modelo.GestorProductos;
import com.emergentes.modelo.Productos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controlador", urlPatterns = {"/controlador"})
public class controlador extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Productos objTarea = new Productos();
        int id;
        int pos;
        String option = request.getParameter("op");
        String op = (option != null) ? request.getParameter("op") : "view";
        if (op.equals(("nuevo"))) {
            HttpSession ses = request.getSession();
            GestorProductos agenda = (GestorProductos) ses.getAttribute("agenda");
            objTarea.setId(agenda.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miTarea", objTarea);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
            
        }
        if (op.equals("modificar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorProductos agenda = (GestorProductos) ses.getAttribute("agenda");
            pos = agenda.ubicarTareas(id);
            objTarea = agenda.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("niTarea", objTarea);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if (op.equals("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorProductos agenda = (GestorProductos) ses.getAttribute("agenda");
            pos = agenda.ubicarTareas(id);
            agenda.eliminarTarea(pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
            
        }
        if (op.equals("view")) {
            response.sendRedirect("index.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Productos objTarea = new Productos();
        int pos;
        String op= request.getParameter("op");
        if(op.equals("grabar")){
            objTarea.setId(Integer.parseInt(request.getParameter("id")));
            objTarea.setDescripcion(request.getParameter("descripcion"));
            objTarea.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            objTarea.setPrecio(Double.parseDouble(request.getParameter("precio")));
            objTarea.setCategoria(request.getParameter("categoria"));
            
            HttpSession ses=request.getSession();
            GestorProductos agenda = (GestorProductos)ses.getAttribute("agenda");
            String opg=request.getParameter("opg");
            if(opg.equals("nuevo")){
                agenda.insertarTarea(objTarea);
            }else{
                pos=agenda.ubicarTareas(objTarea.getId());
                agenda.modificarTarea(pos, objTarea);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }
    
}