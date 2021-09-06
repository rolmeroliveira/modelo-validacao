package br.com.repositorio.repo.config.validacao;

import br.com.repositorio.repo.config.validacao.customvalidexceptions.EstadoObrigatorioException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ValidacaoPorMetodo {

    public static void testaEstado(Long pais, Long estado, EntityManager em) {
        boolean paisTemEstados = paisTemEstados(pais, em);
        boolean estadoNaoInformado = (estado == null || estado == 0);
        boolean estadoNaoPertenceAoPais = estadoNaoPertenceAoPais(pais, estado, em);

        if (estadoNaoInformado) {
            if (paisTemEstados) {
                throw new EstadoObrigatorioException("O Pais tem estados. Precisa informar um estado");
            }
        } else {
            if (estadoNaoPertenceAoPais) {
                throw new EstadoObrigatorioException("O estado informado não pertence ao pais ");
            }
        }
    }

    private static boolean paisTemEstados(Long pais, EntityManager em) {
        Query query = em.createQuery("SELECT 1 FROM Estado  WHERE pais = : pCriterio");
        query.setParameter("pCriterio", pais);
        List<?> existentes = query.getResultList();
        return !existentes.isEmpty();
    }

    private static boolean estadoNaoPertenceAoPais(Long pais, Long estado, EntityManager em) {
        Query query = em.createQuery("SELECT 1 FROM Estado  WHERE pais = :pCriterioPais AND id = :pCriterioEstado");
        query.setParameter("pCriterioPais", pais);
        query.setParameter("pCriterioEstado", pais);
        List<?> existentes = query.getResultList();
        return existentes.isEmpty();
    }
}
