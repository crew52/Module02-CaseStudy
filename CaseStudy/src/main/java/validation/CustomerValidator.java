package validation;

import java.util.regex.Pattern;

public class CustomerValidator {
    // Phương thức xác thực ID
    public static void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    // Phương thức xác thực Name
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    // Phương thức xác thực ContactInfo
//    public static void validateContactInfo(String contactInfo) {
//        if (contactInfo == null || contactInfo.trim().isEmpty()) {
//            throw new IllegalArgumentException("Contact information cannot be null or empty");
//        }
//    }

    public static void validateContactInfo(String contactInfo) {
        if (contactInfo == null || contactInfo.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact information cannot be null or empty");
        }

        // Regex để kiểm tra email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(contactInfo).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    // Phương thức chung để xác thực tất cả các trường
    public static void validate(String id, String name, String contactInfo) {
        validateId(id);
        validateName(name);
        validateContactInfo(contactInfo);
    }
}
