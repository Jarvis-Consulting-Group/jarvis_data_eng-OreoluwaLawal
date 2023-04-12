package ca.jrvs.apps.twitter.dao;

import java.net.URISyntaxException;

public interface CreateDao<T, ID>{
    /**
     * Create an entity(Tweet) to the underlying storage
     * @param entity entity that to be created
     * @return created entity
     */
    T create(ID entity) ;
}
