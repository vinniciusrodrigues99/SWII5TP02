package tp02;
/*
Vinnicius Oliveira Rodrigues - cb3021602
*/
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/produtos/*")
public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdutoDAO produtoDAO;

    public void init() {
        produtoDAO = new ProdutoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();  // Usando getPathInfo para capturar a ação correta

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertProduto(request, response);
                    break;
                case "/delete":
                    deleteProduto(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProduto(request, response);
                    break;
                default:
                    listProdutos(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProdutos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Produto> listProdutos = produtoDAO.listAllProdutos();
        request.setAttribute("listProdutos", listProdutos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProdutoList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProdutoForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto existingProduto = produtoDAO.getProduto(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProdutoForm.jsp");
        request.setAttribute("produto", existingProduto);
        dispatcher.forward(request, response);
    }

    private void insertProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        int unidadeCompra = Integer.parseInt(request.getParameter("unidadeCompra"));
        String descricao = request.getParameter("descricao");
        double qtdPrevistoMes = Double.parseDouble(request.getParameter("qtdPrevistoMes"));
        double precoMaxComprado = Double.parseDouble(request.getParameter("precoMaxComprado"));

        Produto newProduto = new Produto(nome, unidadeCompra, descricao, qtdPrevistoMes, precoMaxComprado);
        produtoDAO.insertProduto(newProduto);
        response.sendRedirect(request.getContextPath() + "/produtos/");
    }

    private void updateProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        int unidadeCompra = Integer.parseInt(request.getParameter("unidadeCompra"));
        String descricao = request.getParameter("descricao");
        double qtdPrevistoMes = Double.parseDouble(request.getParameter("qtdPrevistoMes"));
        double precoMaxComprado = Double.parseDouble(request.getParameter("precoMaxComprado"));

        Produto produto = new Produto(id, nome, unidadeCompra, descricao, qtdPrevistoMes, precoMaxComprado);
        produtoDAO.updateProduto(produto);
        response.sendRedirect(request.getContextPath() + "/produtos/");
    }

    private void deleteProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Produto produto = new Produto(id);
        produtoDAO.deleteProduto(produto);
        response.sendRedirect(request.getContextPath() + "/produtos/");
    }
}
