package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.QuoteDaoIntTest;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.repository.QuoteJpaDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private QuoteDao quoteDao;

    @Before
    public void setup(){
//        quoteDao.deleteAll();
        Quote savedQuote = new Quote();
        savedQuote.setAskPrice(12d);
        savedQuote.setAskSize(12);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setId("MSFT");
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);

        Quote quote2 = new Quote();
        quote2.setAskPrice(12d);
        quote2.setAskSize(12);
        quote2.setBidPrice(10.2d);
        quote2.setBidSize(10);
        quote2.setId("AMZN");
        quote2.setLastPrice(10.1d);
        quoteDao.save(quote2);

        Quote quote3 = new Quote();
        quote3.setAskPrice(12d);
        quote3.setAskSize(12);
        quote3.setBidPrice(10.2d);
        quote3.setBidSize(10);
        quote3.setId("AAPL");
        quote3.setLastPrice(10.1d);
        quoteDao.save(quote3);
    }

    @Test
    public void findIexQuoteByTicker(){
        IexQuote iexQuote = quoteService.findIexQuoteByTicker("AAPL");

        assertEquals("AAPL", iexQuote.getSymbol());
    }

    @Test
    public void updateMarketData(){
       List<Quote> quotes = quoteService.updateMarketData();

        IexQuote iexQuote = quoteService.findIexQuoteByTicker("MSFT");

        assertEquals("MSFT", iexQuote.getSymbol());
        assertEquals("MSFT", quotes.get(0).getId());
    }

    @Test
    public void saveQuotes(){
        List<Quote> quotes = quoteService.saveQuotes(Arrays.asList("TSLA", "GOOGL", "META"));
        assertEquals("TSLA", quotes.get(0).getId());
    }

    @Test
    public void saveQuote(){
        Quote quote = quoteService.saveQuote("TSLA");

        assertEquals("TSLA", quote.getId());
    }
    @Test
    public void findAllQuotes(){
        List<Quote> quotes = quoteService.findAllQuotes();

        assertEquals("MSFT", quotes.get(0).getId());
    }
}
