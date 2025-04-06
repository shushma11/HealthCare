package com.example.healthcare.Service;

import com.example.healthcare.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    MedicineDto addMedicine(MedicineDto medicineDto);
    MedicineDto getMedicineById(Long medicineId);
    MedicineDto getMedicineByMedicineName(String medicineName);
    List<MedicineDto> getAllMedicines();
//    MedicineDto updateMedicine(String medicineName,MedicineDto updatedMedicine);
    MedicineDto updateMedicine(Long medicineId,MedicineDto updatedMedicine);
    void deleteMedicineById(Long medicineId);
}
