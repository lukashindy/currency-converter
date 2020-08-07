package lukashindy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_currency_id")
    private Currency sourceCurrency;

    @ManyToOne
    @JoinColumn(name = "target_currency_id")
    private Currency targetCurrency;

    @Column(name = "source_sum", nullable = false)
    private Double sourceSum;

    @Column(name = "target_sum", nullable = false)
    private Double targetSum;

    @DateTimeFormat
    @Column(name = "date_history", nullable = false)
    private Date date;

    public History(Currency sourceCurrency, Currency targetCurrency, Double sourceSum, Double targetSum, Date date) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceSum = sourceSum;
        this.targetSum = targetSum;
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", sourceCurrency=" + sourceCurrency +
                ", targetCurrency=" + targetCurrency +
                ", sourceSum=" + sourceSum +
                ", targetSum=" + targetSum +
                ", date=" + date +
                '}';
    }
}
