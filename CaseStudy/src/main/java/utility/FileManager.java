package utility;

import java.util.List;

public interface FileManager<T>{
    boolean fileExists(String filename);
    // Ghi danh sách đối tượng vào file
    void writeToFile(List<T> data, String filename);

    // Đọc danh sách đối tượng từ file
    List<T> readFromFile(String filename);

    // Tìm kiếm đối tượng trong file (theo ID hoặc thuộc tính nào đó)
    T searchInFile(String filename, String searchCriteria);

    // Cập nhật đối tượng trong file (thay thế đối tượng cũ bằng đối tượng mới)
    void updateInFile(String filename, T oldEntity, T newEntity);

    // Xóa đối tượng trong file
    void deleteFromFile(String filename, String searchCriteria);
}
