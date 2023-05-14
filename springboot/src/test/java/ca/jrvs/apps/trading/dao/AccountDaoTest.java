package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDaoTest {

    @Autowired
    AccountDao accountDao;
    Account account;

    @Autowired
    private TraderDao traderDao;
    private Trader trader;

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
    }

    @Test
    public void findAllById(){
        List<Account> accounts = Lists.newArrayList(accountDao.findAllById(Arrays.asList(1, -1)));
        assertEquals(account.getTraderId(), accounts.get(0).getId());
        assertTrue(accounts.size() == 1);
    }

    @Test
    public void deleteOne(){
        accountDao.deleteById(account.getId());
        assertTrue(accountDao.count() == 0);
    }
}
