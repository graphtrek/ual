package co.grtk.ual;

import co.grtk.ual.model.UalTemplate;
import co.grtk.ual.repositroy.UalTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class DataLoaderApplication implements CommandLineRunner {

    private final UalTemplateRepository ualTemplateRepository;
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DataLoaderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }

    public void loadData() throws Exception {
        File xlsx = new ClassPathResource("/templates.xlsx").getFile();
        log.info("DataLoaderApplication starting xlsx:{}",xlsx);
        try (FileInputStream file = new FileInputStream(xlsx); ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows = sheet.openStream()) {
                rows.forEach(r -> {
                    String activityCode = r.getOptionalCell(3)
                            .map(Cell::asString)
                            .orElse("");

                    String resultCode = r.getOptionalCell(4)
                            .map(Cell::asString)
                            .orElse("");


                    UalTemplate ualTemplate = ualTemplateRepository
                            .findByActivityCodeAndResultCode(activityCode,resultCode)
                            .orElse( new UalTemplate());

                    String templateName = r.getOptionalCell(0)
                            .map(Cell::asString)
                            .orElse("");

                    ualTemplate.setTemplateName(templateName);
                    ualTemplate.setActivityCode(activityCode);
                    ualTemplate.setResultCode(resultCode);
                    ualTemplateRepository.save(ualTemplate);
                });
            }
        }
    }


    @Override
    public void run(String... args) throws Exception {
        if(args.length > 0 && "loadData".equals(args[0]))
            loadData();
    }
}
