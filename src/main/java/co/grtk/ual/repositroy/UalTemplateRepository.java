package co.grtk.ual.repositroy;

import co.grtk.ual.model.UalTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UalTemplateRepository extends CrudRepository<UalTemplate, Long> {
    public Optional<UalTemplate> findByActivityCodeAndResultCode(String activityCode, String resultCode);
}
