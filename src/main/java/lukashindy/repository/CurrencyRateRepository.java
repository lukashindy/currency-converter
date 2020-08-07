package lukashindy.repository;

import lukashindy.model.Currency;
import lukashindy.model.CurrencyRate;
import lukashindy.model.CurrencyRateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, CurrencyRateId> {

    Optional<CurrencyRate> findByCurrencyIdAndDate(String currencyId, Date date);
    List<CurrencyRate> findAllByDateOrderByCurrencyCharCode(Date date);

}
