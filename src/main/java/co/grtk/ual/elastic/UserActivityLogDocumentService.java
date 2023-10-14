package co.grtk.ual.elastic;


import co.grtk.ual.dto.UserActivityLogDTO;
import co.grtk.ual.dto.UserActivityLogRequestDTO;
import co.grtk.ual.model.TextParamHolder;
import co.grtk.ual.repositroy.UalTemplateRepository;
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
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static co.grtk.ual.elastic.UserActivityLogDocumentMapper.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserActivityLogDocumentService {
    private final UserActivityLogDocumentRepository userActivityLogDocumentRepository;
    private final UalTemplateRepository ualTemplateRepository;
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

             ualTemplateRepository.findByActivityCodeAndResultCode(userActivityLogDocument.getActivityCode(),userActivityLogDocument.getResultCode()).map(ualTemplate -> {
                  List<TextParamHolder> textParamHolders = convertToTextParamHolders(userActivityLogDocument.getTextParams());
                  StringTemplate st = new StringTemplate(ualTemplate.getTemplate(), DefaultTemplateLexer.class);
                  for (TextParamHolder param : textParamHolders) {
                      st.setAttribute(param.getName(), param.getValue());
                  }
                  userActivityLogDTO.setText(st.toString());
                  return userActivityLogDTO;
              });

              list.add(userActivityLogDTO);
          }
        }
        return list;
    }

    public UserActivityLogDocument logUserActivity(UserActivityLogDTO dto) {
        UserActivityLogDocument document = toUserActivityLogDocument(dto);
        return userActivityLogDocumentRepository.findFirstByEventId(dto.getEventId()).orElse(userActivityLogDocumentRepository.save(document));
    }

    public List<UserActivityLogDTO> list() {
        List<UserActivityLogDTO> list = new ArrayList<>();
        userActivityLogDocumentRepository.findAll().forEach(document -> {
            UserActivityLogDTO userActivityLogDTO = toUserActivityLogDTO(document);
            list.add(userActivityLogDTO);
        });
        return list;
    }
}
