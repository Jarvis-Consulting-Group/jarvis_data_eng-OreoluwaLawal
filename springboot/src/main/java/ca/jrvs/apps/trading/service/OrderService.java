package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private AccountDao accountDao;
    private SecurityOrderDao securityOrderDao;
    private QuoteDao quoteDao;
    private PositionDao positionDao;

    @Autowired
    public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao, QuoteDao quoteDao, PositionDao positionDao) {
        this.accountDao = accountDao;
        this.securityOrderDao = securityOrderDao;
        this.positionDao = positionDao;
        this.quoteDao = quoteDao;
    }

    public SecurityOrder executeMarketOrder(MarketOrderDto marketOrderDto) {
        if (marketOrderDto.getSize() <= 0 || marketOrderDto == null || marketOrderDto.getTicker() == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        Quote quote = quoteDao.findById(marketOrderDto.getTicker()).get();
        if (quote == null) {
            throw new IllegalArgumentException("Invalid ticker");
        }
        Account account = accountDao.findById(marketOrderDto.getAccountId()).get();
        if (account == null) {
            throw new IllegalArgumentException("Invalid account id");
        }
        SecurityOrder securityOrder = new SecurityOrder();
        securityOrder.setTicker(marketOrderDto.getTicker());
        securityOrder.setPrice(quote.getLastPrice());
        securityOrder.setStatus("PENDING");
        securityOrder.setSize(marketOrderDto.getSize());
        securityOrder.setNotes(null);
        securityOrder.setAccountId(account.getId());

        if (account.getAmount() - (securityOrder.getPrice() * securityOrder.getSize()) < 0) {
            securityOrder.setStatus("CANCELED");
            securityOrder.setNotes("Insufficient funds. Order amount " + (securityOrder.getSize() * securityOrder.getPrice()) + ". You have: " + account.getAmount());
        }
        else {
            account.setAmount(account.getAmount() - (securityOrder.getPrice() * securityOrder.getSize()));
            accountDao.updateAmountById(account.getId(), account.getAmount());
            securityOrder.setStatus("FILLED");
        }

        SecurityOrder so = securityOrderDao.save(securityOrder);

        return securityOrder;
    }

    protected void handleBuyMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder, Account account) {
        if (account.getAmount() - securityOrder.getPrice() < 0) {
            securityOrder.setStatus("CANCELED");
            securityOrder.setNotes("Insufficient funds. Order amount " + (securityOrder.getSize() * securityOrder.getPrice()) + ". You have: " + account.getAmount());
        }
        account.setAmount(account.getAmount() - securityOrder.getPrice());
        accountDao.updateAmountById(account.getId(), account.getAmount());
        securityOrder.setStatus("FILLED");

    }

}
