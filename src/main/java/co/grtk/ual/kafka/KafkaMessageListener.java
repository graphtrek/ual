package co.grtk.ual.kafka;

import co.grtk.ual.dto.UserActivityLogDTO;
import co.grtk.ual.elastic.UserActivityLogDocument;
import co.grtk.ual.elastic.UserActivityLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaMessageListener {
    private final UserActivityLogService userActivityLogService;

    @KafkaListener(topics = "ActivityLog", groupId = "ActivityLog-Group")
    public void consumeEvents(UserActivityLogDTO userActivityLogDTO) {
        UserActivityLogDocument document = userActivityLogService.logUserActivity(userActivityLogDTO);
        log.info("consumer consume the eventId:{}", document.getEventId());
    }

}
