package lab.academico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

public class AlunoDao {
     
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab-persistence-unit");
    
    public static void inclui(String matricula, String nome, String curso) {
      Aluno aluno = new Aluno();
          aluno.setMatricula(matricula);
          aluno.setNome(nome);
          aluno.setCurso(curso);
      executaOperacao((em) -> {
          em.persist(aluno);
      });
    }
    
    public static void altera(String matricula, String nome, String curso) {
        executaOperacao((em) -> {
            Aluno aluno = em.find(Aluno.class, matricula);
                aluno.setMatricula(matricula);
                aluno.setNome(nome);
                aluno.setCurso(curso);
           em.persist(aluno);
        });
    }
    
    public static void exclui(String matricula) {
        executaOperacao((em) -> {
            em.remove(em.find(Aluno.class, matricula));
        });
    }
    
    public static List<Aluno> listar() {
        EntityManager em = emf.createEntityManager();
        String jpql = "from Aluno";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        List<Aluno> lista = query.getResultList();
        em.close();
        return lista;
    }
    
    private interface operacaoAlunoDao {
        void exc(EntityManager em);
    }
    
    private static void executaOperacao(operacaoAlunoDao op) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            op.exc(em);
        em.getTransaction().commit();
        em.close();
    }
}
