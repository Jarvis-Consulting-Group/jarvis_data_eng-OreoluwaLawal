package ca.jrvs.apps.twitter.dao;

public interface FindDao<T, ID>{
    /**
     * Find an entity(Tweet) by its id
     * @param id entity id
     * @return Tweet entity
     */
    T findById(ID id);
}
