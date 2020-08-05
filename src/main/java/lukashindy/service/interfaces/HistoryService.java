package lukashindy.service.interfaces;

import lukashindy.model.ConverterForm;
import lukashindy.model.History;

public interface HistoryService {

    History saveNewConversion(ConverterForm converterForm);
}
