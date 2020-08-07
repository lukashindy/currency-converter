package lukashindy.service.interfaces;

import lukashindy.model.ConverterForm;
import lukashindy.model.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface HistoryService {

    History saveNewConversion(ConverterForm converterForm);

    List<History> findAll(String source, String target);

//    Page<History> findAll(Pageable pageable);
}
