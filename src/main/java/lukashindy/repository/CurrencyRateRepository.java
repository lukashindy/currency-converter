package lukashindy.repository;

import lukashindy.model.CurrencyRate;
import lukashindy.model.CurrencyRateId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, CurrencyRateId> {

    Optional<CurrencyRate> findByCurrencyIdAndDate(String currencyId, Date date);

    List<CurrencyRate> findAllByDateOrderByCurrencyCharCode(Date date);

    List<CurrencyRate> findAllByDateOrderByCurrencyNameAsc(Date date);

}
