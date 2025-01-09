package entity.main;

import repository.CustomerRepositoryImpl;
import service.CustomerServiceImpl;
import utility.FileManagerImpl;
import view.CustomerView;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo FileManager và CustomerRepository
        FileManagerImpl<Object> fileManager = new FileManagerImpl<>();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl(fileManager);

        // Khởi tạo CustomerServiceImpl
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);

        // Khởi tạo và hiển thị menu
        CustomerView customerView = new CustomerView(customerService);
        customerView.showMenu();
    }
}
