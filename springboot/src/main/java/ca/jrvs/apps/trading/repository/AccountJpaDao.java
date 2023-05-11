package ca.jrvs.apps.trading.repository;

import ca.jrvs.apps.trading.model.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaDao extends JpaRepository<Account, Integer> {
    @Query("select a from Account a where a.trader_id = ?1")
    Account getAccountByTraderId(Integer traderId);

    @Query("update Account set a.amount =?1 where a.id=?2")
    void updateAmountById(Integer id, Double amount);
}
