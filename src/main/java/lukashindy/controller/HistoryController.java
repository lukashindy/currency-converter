package lukashindy.controller;

import lukashindy.model.History;
import lukashindy.repository.CurrencyRateRepository;
import lukashindy.service.interfaces.HistoryService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class HistoryController {

    List<History> histories;

    private final HistoryService historyService;
    private final CurrencyRateRepository currencyRateRepository;

    public HistoryController(HistoryService historyService, CurrencyRateRepository currencyRateRepository) {
        this.historyService = historyService;
        this.currencyRateRepository = currencyRateRepository;
    }

    @GetMapping("/history")
    public String getHistory(Model model,
                             @Param("source") String source,
                             @Param("target") String target,
                             @Param("date") String date) {

        model.addAttribute("currencies", currencyRateRepository.findAllByDateOrderByCurrencyCharCode(new Date()));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date searchDate;

        try {
            searchDate = format.parse(date);
        } catch (ParseException | NullPointerException e) {
            searchDate = null;
        }

        if (searchDate != null) {
            histories = historyService.findAll(source, target, searchDate);
        } else if (source != null || target != null) {
            histories = historyService.findAll(source, target);
        } else {
            histories = historyService.findAllByOrderByIdDesc();
        }
        model.addAttribute("histories", histories);

        return "history";
    }

}
