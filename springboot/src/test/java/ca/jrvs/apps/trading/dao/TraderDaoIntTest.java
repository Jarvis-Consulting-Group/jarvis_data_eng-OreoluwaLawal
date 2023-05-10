package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {

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
    }

    @Test
    public void findAllById(){
        List<Trader> traders = Lists.newArrayList(traderDao.findAllById(Arrays.asList(1, -1)));
        assertEquals(1, traders.size());
        assertEquals(trader.getCountry(), traders.get(0).getCountry());
    }

    @Test
    public void findById(){
        Optional<Trader> traders = traderDao.findById(1);
        assertEquals(trader.getCountry(), traders.get().getCountry());
    }

    @Test
    public void deleteOne(){
        traderDao.deleteById(trader.getId());
        assertTrue(traderDao.count() == 0);
    }
}
