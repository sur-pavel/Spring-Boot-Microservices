package ru.surpavel.springboot.forexservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.core.env.Environment;

@RestController
public class ForexController {
    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retriExchangeValue(@PathVariable String from, 
    @PathVariable String to) {
        ExchangeValue exchangeValue =
        repository.findByFromAndTo(from, to);
        exchangeValue.setPort(
            Integer.parseInt(environment.getProperty(
                "local.server.port"
            ))
        );
        return exchangeValue;
    }
    

}
