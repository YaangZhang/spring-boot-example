package com.yuedu.es.dao;

import com.yuedu.es.entities.EsZjbNote;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author zhy
 * @create 2020-04-09-15:07
 */
@Component
public interface ZjbNoteEsRepository extends ElasticsearchCrudRepository<EsZjbNote, String> {

}
