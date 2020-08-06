package lukashindy.service.interfaces;

import lukashindy.model.ConverterForm;
import lukashindy.model.History;

import java.util.List;

public interface HistoryService {

    History saveNewConversion(ConverterForm converterForm);

    List<History> findAll();
}
