package lab.academico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Data Access Object.
 */
public class ProfessorDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab-persistence-unit");

	public static void inclui(String matricula, String nome) {
	    Professor professor = new Professor();
	        professor.setMatricula(matricula);
	        professor.setNome(nome);
	   executaOperacao((em) -> {
	       em.persist(professor);
	   });
	}

	public static void alterar(String matricula, String nome) {
	    executaOperacao((em) -> {
	        Professor p = em.find(Professor.class, matricula);
	        p.setNome(nome);
	        em.persist(p);
	    });
	}

	public static void excluir(String matricula) {
	    executaOperacao((em) -> {
	        em.remove(em.find(Professor.class, matricula));
	    });
	}

	public static List<Professor> listar() {
		EntityManager em = emf.createEntityManager();
		String jpql = "from Professor";
		TypedQuery<Professor> query = em.createQuery(jpql, Professor.class);
		List<Professor> result = query.getResultList();
		em.close();
		return result;
	}
	
	private interface operacaoProfessorDao {
        void exc(EntityManager em);
    }
    
    private static void executaOperacao(operacaoProfessorDao op) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            op.exc(em);
        em.getTransaction().commit();
        em.close();
    }
}
