package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@Sql({"classpath:schema.sql"})
public class SecurityOrderDaoTest {
    @Autowired
    SecurityOrderDao securityOrderDao;
    SecurityOrder securityOrder;
    @Autowired
    AccountDao accountDao;
    Account account;

    @Autowired
    private TraderDao traderDao;
    private Trader trader;

    @Autowired
    private QuoteDao quoteDao;
    private Quote savedQuote;

    @Before
    public void insertOne(){
        trader = new Trader();
        trader.setFirstName("Oreoluwa");
        trader.setLastName("Lawal");
        trader.setCountry("Canada");
        trader.setEmail("oreo@gmail.com");
        trader.setDob(new Date(System.currentTimeMillis()));
        traderDao.save(trader);

        account = new Account();
        account.setAmount(10.00);
        account.setTraderId(1);
        accountDao.save(account);

        savedQuote = new Quote();
        savedQuote.setAskPrice(12d);
        savedQuote.setAskSize(12);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setId("MSFT");
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);

        securityOrder = new SecurityOrder();
        securityOrder.setAccountId(1);
        securityOrder.setNotes("Notes 1");
        securityOrder.setSize(1);
        securityOrder.setPrice(12.45);
        securityOrder.setStatus("done");
        securityOrder.setTicker("MSFT");
        securityOrderDao.save(securityOrder);
    }

    @Test
    public void findAllById() {
        List<SecurityOrder> securityOrderList = Lists.newArrayList(securityOrderDao.findAllById(Arrays.asList(1, -1)));
        assertEquals(securityOrder.getAccountId(), securityOrderList.get(0).getAccountId());
        assertTrue(securityOrderList.size() == 1);
    }

    @Test
    public void deleteOne(){
        securityOrderDao.deleteById(securityOrder.getId());
        assertTrue(securityOrderDao.count() == 0);
    }
}
