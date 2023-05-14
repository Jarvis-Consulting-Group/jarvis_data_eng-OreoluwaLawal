package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TraderAccountService {
    private TraderDao traderDao;
    private AccountDao accountDao;
    private PositionDao positionDao;
    private SecurityOrderDao securityOrderDao;
    private static final Logger logger = LoggerFactory.getLogger(TraderAccountService.class);

    @Autowired
    public TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao, SecurityOrderDao securityOrderDao){
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityOrderDao = securityOrderDao;
    }

    public TraderAccountView createTraderAndAccount(Trader trader){
        if(trader.getCountry() == null || trader.getDob() == null || trader.getEmail() == null
        || trader.getFirstName() == null || trader.getLastName() == null || trader.getId() != null){
            throw new IllegalArgumentException("Fields missing");
        }
        Trader newTrader = new Trader();
        newTrader.setDob(trader.getDob());
        newTrader.setEmail(trader.getEmail());
        newTrader.setLastName(trader.getLastName());
        newTrader.setFirstName(trader.getFirstName());
        newTrader.setCountry(trader.getCountry());

        logger.error("trader id " + trader.getId() + ", " + newTrader.getId());
        Trader trader1 = traderDao.save(newTrader);

        Account account = new Account();
        account.setTraderId(newTrader.getId());
        account.setAmount(0.00);
        Account account1 = accountDao.save(account);

        TraderAccountView traderAccountView = new TraderAccountView();
        traderAccountView.setAccount(account1);
        traderAccountView.setTrader(trader1);

        return traderAccountView;
    }

    @Transactional
    public void deleteTraderById(Integer traderId){
        Optional<Trader> trader = traderDao.findById(traderId);
        if(trader == null || traderId == null){
            throw new IllegalArgumentException("Trader id does not exists!");
        }
        Account traderAccount = accountDao.findAccountByTraderId(traderId);
        if(traderAccount.getAmount() != 0){
            throw new IllegalArgumentException("Unable to delete, account balance is not 0 ");
        }
        List<Position> positions = positionDao.findPositionByAccountId(traderAccount.getId());

        for (Position p: positions) {
            if(p.getPosition() > 0){
                throw new IllegalArgumentException("Unable to delete, account has open positions ");
            }
        }

        securityOrderDao.deleteByAccountId(traderAccount.getId());
        accountDao.deleteById(traderId);
        traderDao.deleteById(traderId);
    }
    @Transactional
    public Account deposit(Integer traderId, Double fund){
        if(traderId == null || fund <= 0){
            throw new IllegalArgumentException("Trader id field is missing or funds is less than or equal to 0");
        }
//        Optional<Trader> trader = traderDao.findById(traderId);
//        System.out.println(trader.get().getId() + ", " + trader.get().getLastName());
//        if(trader.get().getId() == null){
//            throw new IllegalArgumentException("Trader does not exist");
//        }
        Account account = accountDao.findAccountByTraderId(traderId);
        if(account.getTraderId() == null){
           throw new IllegalArgumentException("Trader does not exist");
        }
        account.setAmount(account.getAmount() + fund);

        accountDao.updateAmountById(traderId, account.getAmount());

        return account;

    }
    @Transactional
    public Account withdraw(Integer traderId, Double funds){
        if(traderId == null || funds <= 0){
            throw new IllegalArgumentException("Trader id field is missing or funds is less than or equal to 0");
        }
        Optional<Trader> trader = traderDao.findById(traderId);
        if(trader.get().getId() == null){
            throw new IllegalArgumentException("Trader does not exist");
        }
        Account account = accountDao.findAccountByTraderId(traderId);
        if(account.getAmount() - funds < 0) throw new IllegalArgumentException("Insufficient balance");
        account.setAmount(account.getAmount() - funds);

        accountDao.updateAmountById(traderId, account.getAmount());

        return account;
    }
}
