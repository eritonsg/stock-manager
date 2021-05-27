package io.stockmanager.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_stocks")
public class Stock {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stocks_id_sequence")
    @SequenceGenerator(name = "stocks_id_sequence", sequenceName = "stocks_table_sequence")
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(length = 80, name = "enterprise_name")
    private String enterpriseName;

    private Double price;
    private Double variation;

    @Column(name = "registered_at")
    private LocalDate date;

}
