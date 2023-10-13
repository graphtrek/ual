package co.grtk.ual.elastic;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface UserActivityLogRepository extends ElasticsearchRepository<UserActivityLogDocument, Long> {

    public Optional<UserActivityLogDocument> findFirstByEventId(String eventId);

}

