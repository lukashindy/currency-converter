package lukashindy.service.interfaces;

import lukashindy.model.ConverterForm;
import lukashindy.model.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface HistoryService {

    History saveNewConversion(ConverterForm converterForm) throws ParserConfigurationException, SAXException, IOException;

    List<History> findAll(String source, String target, Date date);

    List<History> findAll(String source, String target);

    List<History> findAllByOrderByIdDesc();

}
