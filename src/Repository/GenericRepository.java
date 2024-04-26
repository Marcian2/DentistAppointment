package Repository;
import Domain.Identifiable;
import Exceptions.DuplicateItemException;
import Exceptions.ItemNotFound;



public interface GenericRepository<T extends Identifiable<U>, U> {
    void add(T item) throws DuplicateItemException;
    void deleteById(U id) throws ItemNotFound;
    T getItemById(U id) throws ItemNotFound;
    void updateById(U id, T newItem)  throws ItemNotFound;
    Iterable<T> getAll();
    //For lab 3
    public void addItem(T item) throws DuplicateItemException;

    public boolean removeItem(T item);

    public T findItem(U id);
}
