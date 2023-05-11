package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.repository.QuoteJpaDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuoteService {

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao){
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    public List<Quote> updateMarketData() {
        List<Quote> quotes = (List<Quote>) quoteDao.findAll();
        Optional<IexQuote> iexQuotes = null;
        List<Quote> quoteList = new ArrayList<>();

        try {
            for (Quote quote : quotes) {
                iexQuotes = marketDataDao.findById(quote.getTicker());

               quoteList.add(quoteDao.save(buildQuoteFromIexQuote(iexQuotes.get())));
            }
        } catch (DataAccessException | IllegalArgumentException ex){
            throw new RuntimeException(ex);
        }

        return quoteList;
    }

    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote){
        Quote quote = new Quote();
        quote.setTicker((String) iexQuote.getSymbol());
        quote.setAskPrice((Double) iexQuote.getIexAskPrice());
        quote.setAskSize((Integer) iexQuote.getIexAskSize());
        quote.setBidPrice((Double) iexQuote.getIexBidPrice());
        quote.setBidSize((Integer) iexQuote.getIexBidSize());
        quote.setLastPrice((Double) iexQuote.getLatestPrice());

        return quote;
    }

    public List<Quote> saveQuotes(List<String> tickers){
        List<Quote> quotes = new ArrayList<>();
        for (String ticker: tickers) {
            quotes.add(saveQuote(ticker));
        }

        return quotes;
    }

    public Quote saveQuote(String ticker){
        Quote quote1 = null;
        Optional<IexQuote> iexQuote = marketDataDao.findById(String.valueOf(ticker));

        if(iexQuote == null){
            throw new IllegalArgumentException("Ticker not found from IEX");
        }
        if(iexQuote.isPresent()) {
             quote1 = quoteDao.save(buildQuoteFromIexQuote(iexQuote.get()));
        }

        return quote1;
    }

    public IexQuote findIexQuoteByTicker(String ticker){
            Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);

            if(!iexQuote.isPresent()) throw new IllegalArgumentException("ticker is invalid");

            return iexQuote.get();
    }

    public Quote saveQuote(Quote quote){
        return quoteDao.save(quote);
    }

    public List<Quote> findAllQuotes(){
        return (List<Quote>) quoteDao.findAll();
    }
}
