package com.example.healthcare.Service;

import com.example.healthcare.Entity.User;
import com.example.healthcare.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    MedicineDto addMedicine(MedicineDto medicineDto, User user);
    MedicineDto getMedicineById(Long medicineId,Long userId);
    MedicineDto getMedicineByMedicineName(String medicineName);
    List<MedicineDto> getAllMedicines();
//    MedicineDto updateMedicine(String medicineName,MedicineDto updatedMedicine);
    MedicineDto updateMedicine(Long medicineId,MedicineDto updatedMedicine,Long userId);
    void deleteMedicineById(Long medicineId);

    List<MedicineDto> getMedicinesByUser(Long userId);
}
