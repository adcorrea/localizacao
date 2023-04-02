package com.adcorrejr.localizacao.service;


import com.adcorrejr.localizacao.domain.entity.Cidade;
import com.adcorrejr.localizacao.domain.repository.CidadeRepository;
import static com.adcorrejr.localizacao.domain.repository.specs.CidadeSpecs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;


    @Transactional
    public void salvarCidade(){
        var cidade = new Cidade(1L, "São Paulo", 12396372L);
        cidadeRepository.save(cidade);
    }

    public void listarCidadeLikeSort(){
        cidadeRepository.findByNomeLikeUpperSort("%orto%", Sort.by("habitantes"))
                .forEach(System.out::println);
    }

    public void listarCidadeLikePageble(){
        Pageable pageable = PageRequest.of(0, 10);

        cidadeRepository.findByNomeLikeUpperPageble("%orto%", pageable)
                .forEach(System.out::println);
    }

    public void listarCidadeLike(){
        cidadeRepository.findByNomeLike("%orto%").forEach(System.out::println);
        cidadeRepository.findByNomeLikeUpper("p%").forEach(System.out::println);
    }

    public void listarCidadePorNome(){
        cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
    }

    public void listarCidade(){
        cidadeRepository.findAll().forEach(System.out::println);
    }

    public void filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        Example<Cidade> example = Example.of(cidade, matcher);
        cidadeRepository.findAll(example).forEach(System.out::println);
    }

    public void filtroSpecByNome(){

        cidadeRepository.findAll(nomeEqual("Porto Velho")
                                    .and(habitantesGreaterThan(1000L)))
                .forEach(System.out::println);
    }

    public void filtroSpecProperty(){
        cidadeRepository.findAll(propertyEqual("nome","Porto Velho")
                        .and(propertyEqual("habitantes",1000)))
                .forEach(System.out::println);
    }


    public void filtroSpecDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification
                .where((root, query, cb) -> cb.conjunction());

        if(filtro.getId() != null)
            specs = specs.and( idEqual(filtro.getId()));

        if(StringUtils.hasText(filtro.getNome()))
            specs = specs.and(nomeEqual(filtro.getNome()));

        if(filtro.getHabitantes() != null)
            specs = specs.and((habitantesGreaterThan(filtro.getHabitantes())));

        cidadeRepository.findAll(specs)
                .forEach(System.out::println);

    }


    public void findByNomeSqlNative(String nome){
        cidadeRepository
                .findByNomeSQLNative(nome)
                .stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }

}
