package com.example.healthcare.Controller;

import com.example.healthcare.Entity.User;
import com.example.healthcare.Service.MedicineService;
import com.example.healthcare.Service.UserService;
import com.example.healthcare.dto.MedicineDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001",allowedHeaders = "*")
@RestController
@RequestMapping("/api/healthcare")
public class MedicineController {
    private final MedicineService medicineService;
    private final UserService userService;

    public MedicineController(MedicineService medicineService, UserService userService) {
        this.medicineService = medicineService;
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<MedicineDto> addMedicine(
            @RequestBody MedicineDto medicineDto,
            @RequestHeader("X-User-Id") Long userId) {

        User user = userService.getUserById(userId);
        MedicineDto savedMedicine = medicineService.addMedicine(medicineDto, user);
        return new ResponseEntity<>(savedMedicine, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<MedicineDto>> getUserMedicines(
            @RequestHeader("X-User-Id") Long userId) {

        List<MedicineDto> medicineDtos = medicineService.getMedicinesByUser(userId);
        return ResponseEntity.ok(medicineDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<MedicineDto> getMedicineById(
            @PathVariable("id") Long medicineId,
            @RequestHeader("X-User-Id") Long userId) {

        MedicineDto medicineDto = medicineService.getMedicineById(medicineId, userId);
        return ResponseEntity.ok(medicineDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineDto> updateMedicine(
            @PathVariable("id") Long medicineId,
            @RequestBody MedicineDto updatedMedicine,
            @RequestHeader("X-User-Id") Long userId) {

        MedicineDto medicineDto = medicineService.updateMedicine(medicineId, updatedMedicine, userId);
        return ResponseEntity.ok(medicineDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMedicine(
            @PathVariable("id") Long medicineId) {

        medicineService.deleteMedicineById(medicineId);
        return ResponseEntity.ok("Medicine Deleted Successfully");
    }
}


//    @GetMapping("/ByName/{name}")
//    public ResponseEntity<MedicineDto> getByMedicineName(
//            @PathVariable("name") String name,
//            @RequestHeader("X-User-Id") Long userId) {
//
//        MedicineDto medicineDto = medicineService.getMedicineByMedicineName(name, userId);
//        return ResponseEntity.ok(medicineDto);
//    }