package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManagerImpl <T> implements FileManager <T>{
    @Override
    public boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    @Override
    public void writeToFile(List<T> data, String filename) {
        if (!fileExists(filename)) {
            System.out.println("File does not exist. Creating new file...");
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(data);
            System.out.println("Data has been written to file.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public List<T> readFromFile(String filename) {
        if (!fileExists(filename)) {
            System.err.println("File does not exist.");
            return new ArrayList<>(); // Trả về danh sách rỗng nếu không tìm thấy tệp
        }

        List<T> data = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            data = (List<T>) inputStream.readObject();
            System.out.println("Data has been read from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return data;
    }

    @Override
    public T searchInFile(String filename, String searchCriteria) {
        if (!fileExists(filename)) {
            System.err.println("File does not exist.");
            return null; // Không tìm thấy tệp
        }
        List<T> data = readFromFile(filename);
        if (data != null) {
            for (T item : data) {
                // Bạn có thể thay đổi phương thức kiểm tra theo tiêu chí tìm kiếm (ID, name, v.v.)
                if (item.toString().contains(searchCriteria)) {
                    return item;
                }
            }
        }
        return null; // Không tìm thấy
    }

    @Override
    public void updateInFile(String filename, T oldEntity, T newEntity) {
        if (!fileExists(filename)) {
            System.err.println("File does not exist.");
            return;
        }
        List<T> data = readFromFile(filename);
        if (data != null) {
            int index = data.indexOf(oldEntity);
            if (index != -1) {
                data.set(index, newEntity);
                writeToFile(data, filename); // Cập nhật lại file
                System.out.println("Entity updated in file.");
            }
        }
    }

    @Override
    public void deleteFromFile(String filename, String searchCriteria) {
        if (!fileExists(filename)) {
            System.err.println("File does not exist.");
            return;
        }
        List<T> data = readFromFile(filename);
        if (data != null) {
            data = data.stream()
                    .filter(item -> !item.toString().contains(searchCriteria)) // Lọc đối tượng không khớp với tiêu chí
                    .collect(Collectors.toList());
            writeToFile(data, filename); // Cập nhật lại file
            System.out.println("Entity deleted from file.");
        }
    }
}
