package Repository;

import Domain.Identifiable;
import Exceptions.DuplicateItemException;
import Exceptions.ItemNotFound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MemoryRepository <T extends Identifiable<U>,U> implements GenericRepository<T,U> {
    private final Map<U, T> data = new HashMap<>();
    protected ArrayList<T> MemElements= new ArrayList<>();


    public MemoryRepository() {

    }

    @Override
    public void addItem(T item) throws DuplicateItemException {
        for(T aux:MemElements)
            if(aux.getId().equals(item.getId()))
                return;
        MemElements.add(item);
    }
    @Override
    public boolean removeItem(T item) {
        return false;
    }
    public boolean deleteByIdBOOLEAN(T item) {
        return false;
    }
    @Override
    public T findItem(U id){
        return null;
    }
    public Iterable<T> getALlItems() {
        return MemElements;
    }

    @Override
    public void add(T item)  throws DuplicateItemException{
        if (this.data.containsKey(item.getId())) {
            throw new DuplicateItemException("An item with " + item.getId() + " already exists.");
        } else {
            this.data.put(item.getId(), item);
        }
    }


    @Override
    public void deleteById(U id) throws ItemNotFound{
        if (this.data.containsKey(id)) {
            this.data.remove(id);
        } else {
            throw new ItemNotFound("Item " + id + " not found");
        }
    }

    @Override
    public T getItemById(U id) throws NullPointerException,ItemNotFound{
        if (this.data.containsKey(id)) {
            return this.data.get(id);
        } else {
            throw new ItemNotFound("Item with id " + id + " not found");
        }
    }
    @Override
    public void updateById(U id, T newItem) throws ItemNotFound{
        if (this.data.containsKey(id)) {
            this.data.replace(id, newItem);
        }
        throw new ItemNotFound("Item with id " + id + " not found");
    }

    @Override
    public Iterable<T> getAll() {
        return this.data.values();
    }
}
