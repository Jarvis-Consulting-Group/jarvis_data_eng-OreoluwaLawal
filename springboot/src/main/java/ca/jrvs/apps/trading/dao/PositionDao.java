package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PositionDao {
    private static final Logger logger = LoggerFactory.getLogger(PositionDao.class);

    private static final String TABLE_NAME = "position";
    private static final String ID_COLUMN = "account_id";
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public PositionDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String getIdColumnName() {
        return ID_COLUMN;
    }

    public Position save(Position entity){
        throw new UnsupportedOperationException("Not implemented");
    }

    public Optional<Position> findById(Integer id){
        Optional<Position> entity = Optional.empty();
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";

        try{
            entity = Optional.ofNullable(getJdbcTemplate()
                    .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(Position.class), id));
        } catch(IncorrectResultSizeDataAccessException e){
            logger.debug("Can't find trader id: " + id, e);
        }

        return entity;
    }


    public boolean existsById(Integer id){
        if(findById(id).isPresent()) return true;

        return false;
    }

    public List<Position> findPositionByAccountId(Integer id){
        List<Position> positions = new ArrayList<>();
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE account_id=?";

        try{
            positions = getJdbcTemplate().query(selectSql, BeanPropertyRowMapper.newInstance(Position.class), id);
        } catch(IncorrectResultSizeDataAccessException e){
            logger.debug("Can't find trader id: " + id, e);
        }

        return positions;
    }

    public List<Position> findAll() {
        String select_all = "SELECT * FROM " + getTableName();
        List<Position> entities = getJdbcTemplate().query(select_all, BeanPropertyRowMapper.newInstance(Position.class));
        return entities;
    }


    public List<Position> findAllById(Iterable<Integer> id) {
        List<Position> entities = new ArrayList<>();
        for (Integer i: id) {
            Optional<Position> entity = Optional.empty();
            String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";

            try{
                entity = Optional.ofNullable(getJdbcTemplate()
                        .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(Position.class), i));

                entities.add(entity.get());
            } catch(IncorrectResultSizeDataAccessException e){
                logger.debug("Can't find trader id: " + i, e);
            }
        }
        return entities;
    }

    public long count() {
        String query = "SELECT COUNT(*) FROM " + getTableName();

        return getJdbcTemplate().queryForObject(query, long.class);
    }


    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not implemented");
    }


    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
