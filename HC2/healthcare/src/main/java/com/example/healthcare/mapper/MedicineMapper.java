package com.example.healthcare.mapper;

import com.example.healthcare.Entity.Medicine;
import com.example.healthcare.dto.MedicineDto;

public class MedicineMapper {
    public static MedicineDto mapToMedicineDto(Medicine medicine){

        return new MedicineDto(
                medicine.getId(),
                medicine.getMedicineName(),
                medicine.getDosageMg(),
                medicine.getMedicineType(),
                medicine.getIntervalHours(),
                medicine.getStartTime(),
                medicine.getDays()
        );
    }
    public static Medicine mapToMedicine(MedicineDto medicineDto){
        return new Medicine(
                medicineDto.getId(),
                medicineDto.getMedicineName(),
                medicineDto.getDosageMg(),
                medicineDto.getMedicineType(),
                medicineDto.getIntervalHours(),
                medicineDto.getStartTime(),
                medicineDto.getDays()
        );
    }
}
