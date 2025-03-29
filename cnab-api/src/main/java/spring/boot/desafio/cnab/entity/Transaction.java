package spring.boot.desafio.cnab.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name="transaction_tb")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="code")
    private int code;

    @Column(name="date")
    private LocalDate date;

    @Column(name="hour")
    private LocalTime hour;

    @Column(name="value")
    private BigDecimal value;

    @Column(name="cpf")
    private String cpf;

    @Column(name="card")
    private String card;

    @Column(name="time")
    private LocalDate time;

    @Column(name="store_owner")
    private String storeOwner;

    @Column(name="store_name")
    private String storeName;

    @Column(name="transaction_nature")
    private String transactionNature;

    @Column(name="transaction_signal")
    private String transactionSignal;

    @Column(name="transaction_description")
    private String transactionDescription;
}
