package lukashindy.repository;

import lukashindy.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByOrderByIdDesc();

    @Query("SELECT h from History h " +
            "where h.sourceCurrency.charCode like %:source% " +
            "and h.targetCurrency.charCode like %:target% " +
            "order by h.id desc")
    List<History> findAll(@Param("source") String source,
                          @Param("target") String target);

    @Query("SELECT h from History h " +
            "where h.sourceCurrency.charCode like %:source% " +
            "and h.targetCurrency.charCode like %:target% " +
            "and h.date = :date " +
            "order by h.id desc")
    List<History> findAll(@Param("source") String source,
                          @Param("target") String target,
                          @Param("date") Date date);
}
