package co.grtk.ual.elastic;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserActivityLogRepository extends ElasticsearchRepository<UserActivityLogDocument, Long> {

}

