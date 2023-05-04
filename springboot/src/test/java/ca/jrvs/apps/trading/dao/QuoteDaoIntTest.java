package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

    @Autowired
    private QuoteDao quoteDao;
    private Quote savedQuote;

    @Before
    public void insert() throws Exception {
        savedQuote = new Quote();
        savedQuote.setAskPrice(12d);
        savedQuote.setAskSize(12);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setId("fb2");
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);

        Quote quote1 = new Quote();
        quote1.setAskPrice(12d);
        quote1.setAskSize(12);
        quote1.setBidPrice(10.2d);
        quote1.setBidSize(10);
        quote1.setId("mtl");
        quote1.setLastPrice(10.1d);
        quoteDao.save(quote1);

        Quote quote2 = new Quote();
        quote2.setAskPrice(12d);
        quote2.setAskSize(12);
        quote2.setBidPrice(10.2d);
        quote2.setBidSize(10);
        quote2.setId("fb3");
        quote2.setLastPrice(10.1d);
        quoteDao.save(quote2);

        Quote quote3 = new Quote();
        quote3.setAskPrice(12d);
        quote3.setAskSize(12);
        quote3.setBidPrice(10.2d);
        quote3.setBidSize(10);
        quote3.setId("aapl");
        quote3.setLastPrice(10.1d);
        quoteDao.save(quote3);
    }

    @Test
    public void save() {
        assertEquals(savedQuote.getId(), "fb2");
        assertEquals((int) savedQuote.getAskSize(), 12);
        assertEquals((int) savedQuote.getBidSize(), 10);
    }

    @Test
    public void findById(){
        Optional<Quote> quote = quoteDao.findById("mtl");
        assertEquals("mtl", quote.get().getId());
    }

    @Test
    public void existById(){
        assertTrue(quoteDao.existsById("fb2"));
        assertTrue(quoteDao.existsById("mtl"));
    }

    @Test
    public void findAll(){
        List<Quote> quotes = (List<Quote>) quoteDao.findAll();
        assertEquals(quotes.get(0).getId(), "fb2");
    }
    @Test
    public void deleteOne(){
        assertTrue(quoteDao.existsById("aapl"));
        quoteDao.deleteById("aapl");
        assertFalse(quoteDao.existsById("aapl"));
    }

    @Test
    public void count(){
        long count = quoteDao.count();
        assertEquals(4, count);
    }

    @Test
    public void deleteAll(){
        quoteDao.deleteAll();
    }

}
