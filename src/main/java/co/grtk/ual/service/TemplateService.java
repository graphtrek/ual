package co.grtk.ual.service;

import co.grtk.ual.repositroy.UalTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TemplateService {

    private final UalTemplateRepository ualTemplateRepository;
}
