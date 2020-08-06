package lukashindy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.postgresql.util.PGmoney;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "currency_rate")
@IdClass(CurrencyRateId.class)
public class CurrencyRate implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Id
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "value", nullable = false)
    private Double value;

    public CurrencyRate(Currency currency, Date date, Double value) {
        this.currency = currency;
        this.date = date;
        this.value = value;
    }

}
