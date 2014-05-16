package shared.dao;

/**
 * @author  Dino Opijac
 * @since   16/05/14
 */
public interface IDao<T> {
    T get(Object key);
    T insert(T object);
    boolean update(T object);
    boolean delete(T object);
}
