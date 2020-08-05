package lukashindy.controller;

import lukashindy.service.interfaces.CurrencyRateService;
import lukashindy.service.interfaces.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyRateService currencyRateService;

    public CurrencyController(CurrencyService currencyService, CurrencyRateService currencyRateService) {
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
        return "converter-form";
    }

}
