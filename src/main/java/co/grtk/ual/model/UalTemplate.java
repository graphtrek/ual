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
public class UalTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(length = 4096, nullable = false)
    public String template;

    @Column(length = 255, nullable = false)
    public String activityCode;

    @Column(length = 64, nullable = false)
    public String resultCode;

}
