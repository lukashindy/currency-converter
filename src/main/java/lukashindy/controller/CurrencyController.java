package lukashindy.controller;

import lukashindy.model.ConverterForm;
import lukashindy.service.interfaces.CurrencyRateService;
import lukashindy.service.interfaces.CurrencyService;
import lukashindy.service.interfaces.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class CurrencyController {

    private final HistoryService historyService;
    private final CurrencyService currencyService;
    private final CurrencyRateService currencyRateService;

    public CurrencyController(HistoryService historyService, CurrencyService currencyService, CurrencyRateService currencyRateService) {
        this.historyService = historyService;
        this.currencyService = currencyService;
        this.currencyRateService = currencyRateService;
    }

    @GetMapping("/currencies")
    public String getCurrencies(Model model) {
        model.addAttribute("rates", currencyRateService.findAll());
        return "currency";
    }

    @GetMapping("/converter/form")
    public String getForm(Model model) {
        model.addAttribute("list", currencyRateService.findAll());
        model.addAttribute("history", new ConverterForm());
        return "converter-form";
    }

    @PostMapping("/converter/form")
    public String saveNewConversion(@ModelAttribute("history") ConverterForm converterForm, Model model) {
        model.addAttribute("savedHistory", historyService.saveNewConversion(converterForm));
//        historyService.saveNewConversion(converterForm);
        return "redirect:/converter/form?success";
    }

}
