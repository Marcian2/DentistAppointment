package Repository;
import Domain.Identifiable;
import Exceptions.DuplicateItemException;
import Exceptions.ItemNotFound;

import java.io.BufferedReader;
import java.io.FileReader;
public abstract class FileRepository<T extends Identifiable<U>,U>extends MemoryRepository<T,U>{
    public String filename;

    public FileRepository(String filename){
        super();
        this.filename=filename;
        readfromFile();
    }

    protected abstract void readfromFile();
    protected abstract void writeToFile();

    public void addItem(T item) throws DuplicateItemException {
        super.add(item);
        writeToFile();
    }

    public boolean removeItem(T item) {
        boolean result= super.deleteByIdBOOLEAN(item);
        writeToFile();
        return  result;
    }
}
