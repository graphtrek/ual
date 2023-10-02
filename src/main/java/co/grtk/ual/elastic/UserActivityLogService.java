package co.grtk.ual.elastic;


import co.grtk.ual.dto.UserActivityLogDTO;
import co.grtk.ual.dto.UserActivityLogRequestDTO;
import co.grtk.ual.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static co.grtk.ual.elastic.UserActivityLogMapper.toUserActivityLogDTO;
import static co.grtk.ual.elastic.UserActivityLogMapper.toUserActivityLogDocument;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserActivityLogService {
    private final UserActivityLogRepository userActivityLogRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public List<UserActivityLogDTO> list(UserActivityLogRequestDTO userActivityLogRequestDTO) {
        List<UserActivityLogDTO> list = new ArrayList<>();
        Date fromTs = DateUtil.toDate(userActivityLogRequestDTO.getFromTs());
        Date toTS = DateUtil.toDate(userActivityLogRequestDTO.getToTs());
        Criteria criteria = new Criteria("timeStamp").between(fromTs, toTS);
        Query query = new CriteriaQuery(criteria);
        SearchHits<UserActivityLogDocument> searchHits = elasticsearchOperations.search(query,UserActivityLogDocument.class);
        log.info("Search fromTs:{} toTs:{} searchHits:{}",fromTs, toTS, searchHits.getTotalHits());
        if (searchHits.getTotalHits() > 0) {
          for(SearchHit<UserActivityLogDocument> searchHit : searchHits ) {
              UserActivityLogDocument userActivityLogDocument = searchHit.getContent();
              UserActivityLogDTO userActivityLogDTO = toUserActivityLogDTO(userActivityLogDocument);
              list.add(userActivityLogDTO);
          }
        }
        return list;
    }

    public void logUserActivity(UserActivityLogDTO dto) {
        UserActivityLogDocument document = toUserActivityLogDocument(dto);
        userActivityLogRepository.save(document);
    }

    public List<UserActivityLogDTO> list() {
        List<UserActivityLogDTO> list = new ArrayList<>();
        userActivityLogRepository.findAll().forEach(document -> {
            UserActivityLogDTO userActivityLogDTO = toUserActivityLogDTO(document);
            list.add(userActivityLogDTO);
        });
        return list;
    }
}
