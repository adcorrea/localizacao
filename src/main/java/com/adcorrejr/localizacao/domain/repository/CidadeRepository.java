package com.adcorrejr.localizacao.domain.repository;

import com.adcorrejr.localizacao.domain.entity.Cidade;
import com.adcorrejr.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {


    List<Cidade> findByNome(String nome);


    @Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cliente c where c.nome =:nome ")
    List<CidadeProjection> findByNomeSQLNative(@Param("nome") String nome);

    List<Cidade> findByNomeStartingWith(String nome);

    List<Cidade> findByNomeEndingWith(String nome);

    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThanAndNomeLike(Long habitantes, String nome);

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);

    List<Cidade> findByNomeLike(String nome);

    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    Page<Cidade> findByNomeLikeUpperPageble(String nome, Pageable sort);

    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLikeUpper(String nome);

    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLikeUpperSort(String nome, Sort sort);

    List<Cidade> findByHabitantes(Long habitantes);
}
