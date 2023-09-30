package co.grtk.ual.elastic;

import co.grtk.ual.dto.UserActivityLogDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static co.grtk.ual.elastic.UserActivityLogMapper.toUserActivityLogDTO;
import static co.grtk.ual.elastic.UserActivityLogMapper.toUserActivityLogDocument;

@AllArgsConstructor
@Service
public class UserActivityLogService {
    private final UserActivityLogRepository userActivityLogRepository;

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
