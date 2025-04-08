package com.example.healthcare.Service.impl;

import com.example.healthcare.Entity.Medicine;
import com.example.healthcare.Entity.User;
import com.example.healthcare.Repository.MedicineRepository;
import com.example.healthcare.Service.MedicineService;
import com.example.healthcare.dto.MedicineDto;
import com.example.healthcare.exceptions.ResourceNotFound;
import com.example.healthcare.mapper.MedicineMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public MedicineDto addMedicine(MedicineDto medicineDto, User user) {
        Medicine medicine= MedicineMapper.mapToMedicine(medicineDto);
        medicine.setUser(user);
        Medicine savedMedicine=medicineRepository.save(medicine);
        return MedicineMapper.mapToMedicineDto(savedMedicine);
    }

    @Override
    public MedicineDto getMedicineById(Long medicineId,Long userId) {
        Medicine medicine=medicineRepository.findById(medicineId)
                .orElseThrow(()-> new ResourceNotFound("Medicine does not exists with given id: "+medicineId));
        return MedicineMapper.mapToMedicineDto(medicine);
    }

    @Override
    public MedicineDto getMedicineByMedicineName(String medicineName) {
        Medicine medicine=medicineRepository.findByMedicineName(medicineName)
                .orElseThrow(()-> new ResourceNotFound("Medicine does not exists with given name: "+medicineName));
        return MedicineMapper.mapToMedicineDto(medicine);
    }

    @Override
    public List<MedicineDto> getAllMedicines() {
        List<Medicine> medicines=medicineRepository.findAll();
        List<MedicineDto> medicineDtos=medicines.stream()
                .map(MedicineMapper::mapToMedicineDto)
                .collect(Collectors.toList());
        return  medicineDtos;
    }

//    @Override
//    public MedicineDto updateMedicine(Long medicineId, MedicineDto updatedMedicine) {
//        Medicine medicine=medicineRepository.findById(medicineId)
//                .orElseThrow(()-> new ResourceNotFound("Medicine does not exists with given id: "+medicineId));
//        medicine.setMedicineName(updatedMedicine.getMedicineName());
//        medicine.setDosageMg(updatedMedicine.getDosageMg());
//        medicine.setMedicineType(updatedMedicine.getMedicineType());
//        medicine.setIntervalHours(updatedMedicine.getIntervalHours());
//        medicine.setStartTime(updatedMedicine.getStartTime());
//        medicine.setDays(updatedMedicine.getDays());
//        Medicine upMedicine=medicineRepository.save(medicine);
//        return MedicineMapper.mapToMedicineDto(upMedicine);
//    }
@Override
public MedicineDto updateMedicine(Long medicineId, MedicineDto updatedMedicine, Long userId) {
    Medicine medicine = medicineRepository.findById(medicineId)
            .orElseThrow(() -> new ResourceNotFound("Medicine does not exist with given id: " + medicineId));
    // Optional: Check if the user is authorized to update this medicine
//    if (!medicine.getUserId().equals(userId)) {
//        throw new UnauthorizedException("You are not authorized to update this medicine");
//    }

    medicine.setMedicineName(updatedMedicine.getMedicineName());
    medicine.setDosageMg(updatedMedicine.getDosageMg());
    medicine.setMedicineType(updatedMedicine.getMedicineType());
    medicine.setIntervalHours(updatedMedicine.getIntervalHours());
    medicine.setStartTime(updatedMedicine.getStartTime());
    medicine.setDays(updatedMedicine.getDays());

    Medicine upMedicine = medicineRepository.save(medicine);
    return MedicineMapper.mapToMedicineDto(upMedicine);
}


    @Override
    public void deleteMedicineById(Long medicineId) {
        Medicine medicine=medicineRepository.findById(medicineId)
                .orElseThrow(()-> new ResourceNotFound("Medicine does not exists with given id: "+medicineId));
        medicineRepository.deleteById(medicineId);
    }

    @Override
    public List<MedicineDto> getMedicinesByUser(Long userId) {
        List<Medicine> medicines=medicineRepository.findByUserId(userId);
        List<MedicineDto> medicineDtos=medicines.stream()
                .map(MedicineMapper::mapToMedicineDto)
                .collect(Collectors.toList());
        return  medicineDtos;
    }
}
