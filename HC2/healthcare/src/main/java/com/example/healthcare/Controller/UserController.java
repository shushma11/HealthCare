package com.example.healthcare.Controller;

import com.example.healthcare.Entity.Medicine;
import com.example.healthcare.Entity.User;
import com.example.healthcare.Repository.UserRepository;
import com.example.healthcare.Service.UserService;
import com.example.healthcare.dto.UserLoginDto;
import com.example.healthcare.dto.UserRegistrationDto;
import com.example.healthcare.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3001",allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        User registeredUser = userService.registerUser(registrationDto);
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setName(registeredUser.getName());
        responseDto.setPhoneNumber(registeredUser.getPhoneNumber());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto loginRequest) {
        String phoneNumber = loginRequest.getPhoneNumber();
        String password = loginRequest.getPassword();

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            User user = userRepository.findByPhoneNumber(phoneNumber)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok(Map.of(
                        "message", "User exists",
                        "userId", user.getId()
                ));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }




    @GetMapping("/{userId}/medicines")
    public ResponseEntity<List<Medicine>> getUserMedicines(@PathVariable Long userId) {
        List<Medicine> medicines = userService.getUserMedicines(userId);
        return ResponseEntity.ok(medicines);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<User> getUserByPhone(@PathVariable String phoneNumber) {
        User user = userService.getUserByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(user);
    }
}





//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userLoginDto){
//        String phoneNumber=userLoginDto.getPhoneNumber();
//        String password=userLoginDto.getPassword();
//        String resp=userService.loginUser(phoneNumber,password);
//        return ResponseEntity.ok(resp);
//    }
//@PostMapping("/login")
//public ResponseEntity<?> login(@RequestBody UserLoginDto loginRequest) {
//    Map<String, Object> result = userService.loginUser(
//            loginRequest.getPhoneNumber(),
//            loginRequest.getPassword()
//    );
//
//    if ("User exists".equals(result.get("message"))) {
//        return ResponseEntity.ok(result);
//    } else {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
//    }
//}