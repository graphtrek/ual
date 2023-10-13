package co.grtk.ual.service;

import co.grtk.ual.repositroy.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
}
