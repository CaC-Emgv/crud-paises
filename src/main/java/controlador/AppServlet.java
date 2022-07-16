package controlador;

import modelo.Pais;
import modelo.Modelo;
import modelo.ModeloFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AppServlet", urlPatterns = {"/app"})
public class AppServlet extends HttpServlet {

    private Modelo model;
    private final String URI_LIST = "listadoPaises.jsp";
    private final String URI_REMOVE = "WEB-INF/pages/paises/borrarPais.jsp";
    private final String URI_EDIT = "WEB-INF/pages/paises/editarPais.jsp";

    @Override
    public void init() throws ServletException {
        this.model = getModelo();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        int elId;
        Pais pais;
        accion = accion == null ? "" : accion;
        switch (accion) {
            case "edit":
                elId = Integer.parseInt(request.getParameter("id"));
                pais = model.getPais(elId);
                request.setAttribute("paisAEditar", pais);
                request.setAttribute("yaTieneBandera", !pais.getBandera().contains("no-flag"));
                request.getRequestDispatcher(URI_EDIT).forward(request, response);
                break;
            case "remove":
                elId = Integer.parseInt(request.getParameter("id"));
                pais = model.getPais(elId);
                request.setAttribute("paisABorrar", pais);
                request.getRequestDispatcher(URI_REMOVE).forward(request, response);
                break;
            default:
                HttpSession sesionHttp = request.getSession();
                sesionHttp.setAttribute("listaPaises", model.getPaises());
                //request.getRequestDispatcher(URI_LIST).forward(request, response);
                response.sendRedirect(URI_LIST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pais pais;
        int elId;
        String accion = request.getParameter("accion");
        accion = accion == null ? "" : accion;
        switch (accion) {
            case "add":
                pais = new Pais();
                cargarPaisSegunParams(pais, request);
                model.addPais(pais);
                break;
            case "update":
                elId = Integer.parseInt(request.getParameter("id"));
                pais = model.getPais(elId);
                cargarPaisSegunParams(pais, request);
                model.updatePais(pais);
                break;
            case "delete":
                elId = Integer.parseInt(request.getParameter("id"));
                model.removePais(elId);
                break;
        }
        doGet(request, response);
    }

    private void cargarPaisSegunParams(Pais pais, HttpServletRequest request) {
        pais.setNombre(request.getParameter("nombre"));
        pais.setPoblacion(Integer.parseInt(request.getParameter("poblacion")));
        pais.setSuperficie(Integer.parseInt(request.getParameter("superficie")));
        pais.setfechaIndependencia(request.getParameter("fechaInd"));
        pais.setBandera(request.getParameter("fotoBandera"));
    }

    private Modelo getModelo() throws ServletException {
        Modelo m = null;
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(is);
            String tipoModelo = props.getProperty("tipoModelo");
            m = ModeloFactory.getInstance().crearModelo(tipoModelo);
        } catch (IOException ex) {
            throw new ServletException("Error de E/S al al leer 'config' para iniciar el Servlet", ex);
        }
        return m;
    }
}
