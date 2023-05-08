package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer>{
    private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);
    abstract public JdbcTemplate getJdbcTemplate();
    abstract public SimpleJdbcInsert getSimpleJdbcInsert();
    abstract public String getTableName();
    abstract public String getIdColumnName();
    abstract Class<T> getEntityClass();

    @Override
    public <S extends T> S save(S entity){
        if(existsById(entity.getId())){
            if(updateOne(entity) != 1){
                throw new DataRetrievalFailureException("Unable to update quote");
            }
        }
        else {
            addOne(entity);
        }
        return entity;
    }

    private <S extends T> void addOne(S entity){
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

        Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
        entity.setId(newId.intValue());
    }

    abstract public int updateOne(T entity);

    @Override
    public Optional<T> findById(Integer id){
        Optional<T> entity = Optional.empty();
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";

        try{
            entity = Optional.ofNullable((T) getJdbcTemplate()
                    .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()), id));
        } catch(IncorrectResultSizeDataAccessException e){
            logger.debug("Can't find trader id: " + id, e);
        }

        return entity;
    }
    @Override
    public boolean existsById(Integer id){
        if(findById(id).isPresent()) return true;

        return false;
    }
    @Override
    public List<T> findAll() {
        String select_all = "SELECT * FROM " + getTableName();
        List<T> entities = getJdbcTemplate().query(select_all, BeanPropertyRowMapper.newInstance(getEntityClass()));
        return entities;
    }

    @Override
    public List<T> findAllById(Iterable<Integer> id) {
        List<T> entities = new ArrayList<>();
        for (Integer i: id) {
            Optional<T> entity = Optional.empty();
            String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";

            try{
                entity = Optional.ofNullable((T) getJdbcTemplate()
                        .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()), i));

                entities.add(entity.get());
            } catch(IncorrectResultSizeDataAccessException e){
                logger.debug("Can't find trader id: " + i, e);
            }
        }
        return entities;
    }
    @Override
    public long count() {
        String query = "SELECT COUNT(*) FROM " + getTableName();

        return getJdbcTemplate().queryForObject(query, long.class);
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
        getJdbcTemplate().update(query, id);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM " + getTableName();
        getJdbcTemplate().update(query);
    }
}
