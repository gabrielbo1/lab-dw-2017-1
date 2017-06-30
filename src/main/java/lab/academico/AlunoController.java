package lab.academico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exemplo-jpa/aluno")
public class AlunoController extends HttpServlet {
    
    private String valor(HttpServletRequest req, String param, String padrao) {
        String result = req.getParameter(param);
        if (result == null) {
            result = padrao;
        }
        return result;
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String msg = "";
            String op = valor(req, "operacao", "");
            String matricula = valor(req, "matricula", "");
            String nome = valor(req, "nome", "");
            String curso = valor(req, "curso", "");
            
            switch(op) {
               /** DISPACHER DO JSP.*/
               case "":
               break; 
               
               case "incluir":
                    AlunoDao.inclui(matricula, nome, curso);
                    msg = "Operação realizada com sucesso, aluno cadastrado";
                break;
                
                case "alterar":
                    AlunoDao.altera(matricula, nome, curso);
                    msg = "Operação realizada com sucesso, informações alteradas";
                break;
                
                case "excluir":
                    AlunoDao.exclui(matricula);
                    msg = "Operação realizada com sucesso, matricula " + matricula + " excluida";
                break;
                
                default:
                    throw new IllegalArgumentException("Operação \"" + op + "\" não suportada.");
                
            }
            
            req.setAttribute("msg", msg);
            req.setAttribute("alunos", AlunoDao.listar());
            
            req.getRequestDispatcher("AlunoView.jsp").forward(req, resp);
        } catch(Exception e) {
            e.printStackTrace(resp.getWriter());
        }
    }
}
