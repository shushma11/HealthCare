package com.example.healthcare.Service;

import com.example.healthcare.Entity.Medicine;
import com.example.healthcare.Entity.User;
import com.example.healthcare.Repository.MedicineRepository;
import com.example.healthcare.Repository.UserRepository;
import com.example.healthcare.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;
//    private final PasswordEncoder passwordEncoder;
//    , PasswordEncoder passwordEncoder

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;

    public UserService(UserRepository userRepository, MedicineRepository medicineRepository) {
        this.userRepository = userRepository;
        this.medicineRepository = medicineRepository;
    }

    ////        this.passwordEncoder = passwordEncoder;
//    }


    public User registerUser(UserRegistrationDto dto) {
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new RuntimeException("Phone number already registered");
        }

        User user = new User(dto.getName(), dto.getPhoneNumber(), dto.getPassword());
        return userRepository.save(user);
    }


    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

//    public String loginUser(String phoneNumber,String password){
//        if(userRepository.existsByPhoneNumber(phoneNumber)){
//            User user=userRepository.findByPhoneNumber(phoneNumber)
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//            if(user.getPassword().equals(password)){
//                return "User exists";
//            }
//        }
//        return "User doesn't exist";
//    }
public Map<String, Object> loginUser(String phoneNumber, String password) {
    Map<String, Object> response = new HashMap<>();

    if (userRepository.existsByPhoneNumber(phoneNumber)) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getPassword().equals(password)) {
            response.put("message", "User exists");
            response.put("userId", user.getId());
            return response;
        }
    }

    response.put("message", "User doesn't exist");
    return response;
}

    public List<Medicine> getUserMedicines(Long userId) {
        return medicineRepository.findByUserId(userId);
    }
}



