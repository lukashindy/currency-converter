package lukashindy.controller;

import lukashindy.model.History;
import lukashindy.repository.CurrencyRateRepository;
import lukashindy.repository.HistoryRepository;
import lukashindy.search.HistorySearchValues;
import lukashindy.service.interfaces.HistoryService;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class HistoryController {

    List<History> histories;

    private final HistoryService historyService;
    private final HistoryRepository historyRepository;
    private final CurrencyRateRepository currencyRateRepository;

    public HistoryController(HistoryService historyService, HistoryRepository historyRepository, CurrencyRateRepository currencyRateRepository) {
        this.historyService = historyService;
        this.historyRepository = historyRepository;
        this.currencyRateRepository = currencyRateRepository;
    }

    @GetMapping("/history")
    public String getHistory(Model model,
                             @Param("source") String source,
                             @Param("target") String target) {

        model.addAttribute("currencies", currencyRateRepository.findAllByDateOrderByCurrencyCharCode(new Date()));

        if (source != null || target != null ) {
            histories = historyService.findAll(source, target);
        } else {
            histories = historyRepository.findAllByOrderByDateDesc();
        }
        model.addAttribute("histories", histories);
        return "history";
    }


    @PostMapping("/history")
    public String searchHistory() {
        return "redirect:/history";
    }


//    @GetMapping("/history/{}")
//    public String searchHistory() {
//        return "redirect:/history";
//    }

}
