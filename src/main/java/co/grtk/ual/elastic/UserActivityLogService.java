package co.grtk.ual.elastic;

import co.grtk.ual.dto.UserActivityLogDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

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
        return StreamSupport.stream(
                        userActivityLogRepository.findAll().spliterator(), false)
                .map(UserActivityLogMapper::toUserActivityLogDTO)
                .toList();
    }
}
