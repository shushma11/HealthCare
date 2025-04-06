package com.example.healthcare.Controller;

import com.example.healthcare.Entity.Medicine;
import com.example.healthcare.Service.MedicineService;
import com.example.healthcare.dto.MedicineDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/healthcare")
public class MedicineController {
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
    @PostMapping
    public ResponseEntity<MedicineDto> addMedicine(@RequestBody MedicineDto medicineDto){
        MedicineDto savedMedicine=medicineService.addMedicine(medicineDto);
        return new ResponseEntity<>(savedMedicine, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public  ResponseEntity<MedicineDto> getMedicineById(@PathVariable("id") Long medicineId){
        MedicineDto medicineDto1=medicineService.getMedicineById(medicineId);
        return ResponseEntity.ok(medicineDto1);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicineDto>> getAllMedicines(){
        List<MedicineDto> medicineDtos=medicineService.getAllMedicines();
        return ResponseEntity.ok(medicineDtos);
    }
    @GetMapping("/ByName/{name}")
    public ResponseEntity<MedicineDto> getByMedicineName(@PathVariable("name") String name){
        MedicineDto medicineDto=medicineService.getMedicineByMedicineName(name);
        return ResponseEntity.ok(medicineDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MedicineDto> updateMedicine(@PathVariable("id") Long medicineId,@RequestBody MedicineDto updatedMedicine){
        MedicineDto medicineDto=medicineService.updateMedicine(medicineId,updatedMedicine);
        return ResponseEntity.ok(medicineDto);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteMedicine(@PathVariable("id") Long medicineId){
        medicineService.deleteMedicineById(medicineId);
        return ResponseEntity.ok("Medicine Deleted Successfully");
    }
}
