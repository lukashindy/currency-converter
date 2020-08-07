package lukashindy.repository;

import lukashindy.model.History;
import lukashindy.search.HistorySearchValues;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
//    Page<History> findAll(Pageable pageable);

    List<History> findAllByOrderByDateDesc();

//    List<History> findAllBySourceCurrencyCharCodeOrTargetCurrencyCharCodeOrDateOrderByDate(HistorySearchValues values);

    @Query("SELECT h from History h " +
            "where h.sourceCurrency.charCode like %:source% " +
            "and h.targetCurrency.charCode like %:target% " +
//            "and h.date = :date " +
//            "and h.date like %:date%" +
            "order by h.date desc")
    List<History> findAll(@Param("source") String source,
                          @Param("target") String target);
//                          @Param("date") Date date);

}
