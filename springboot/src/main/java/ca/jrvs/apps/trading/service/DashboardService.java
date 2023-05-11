package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.*;
import ca.jrvs.apps.trading.model.dto.PortfolioView;
import ca.jrvs.apps.trading.model.dto.SecurityRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DashboardService {

    private TraderDao traderDao;
    private PositionDao positionDao;
    private AccountDao accountDao;
    private QuoteDao quoteDao;

    @Autowired
    public DashboardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao, QuoteDao quoteDao){
        this.traderDao = traderDao;
        this.positionDao = positionDao;
        this.accountDao = accountDao;
        this.quoteDao = quoteDao;
    }

    public TraderAccountView getTraderAccount(Integer traderId){
        if(traderId == null){
            throw new IllegalArgumentException("Trader id is null or not found");
        }
        Account account = accountDao.findAccountByTraderId(traderId);
        Trader trader = traderDao.findById(traderId).get();
        if(trader == null || account == null){
            throw new IllegalArgumentException("Trader does not exist");
        }
        TraderAccountView traderAccountView = new TraderAccountView();
        traderAccountView.setTrader(trader);
        traderAccountView.setAccount(account);

        return traderAccountView;
    }

    public PortfolioView getProfileViewByTraderId(Integer traderId){
        if(traderId == null){
            throw new IllegalArgumentException("Trader id is null or not found");
        }
        Account account = accountDao.findAccountByTraderId(traderId);

        if(account == null){
            throw new IllegalArgumentException("Trader does not exist");
        }
        List<Position> positions = positionDao.findPositionByAccountId(account.getId());
        List<SecurityRows> sr = new ArrayList<>();
        for (Position p: positions) {
            Quote quote = quoteDao.findById(p.getTicker()).get();
            SecurityRows securityRows = new SecurityRows();
            securityRows.setTicker(p.getTicker());
            securityRows.setPosition(p);
            securityRows.setQuote(quote);

            sr.add(securityRows);
        }

        PortfolioView portfolioView = new PortfolioView();
        portfolioView.setSecurityRows(sr);

        return portfolioView;
    }

}
