package co.grtk.ual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(length = 4096, nullable = false)
    String templateName;

    @Column(length = 255, nullable = false)
    String activityCode;

    @Column(length = 32, nullable = false)
    String resultCode;

}
