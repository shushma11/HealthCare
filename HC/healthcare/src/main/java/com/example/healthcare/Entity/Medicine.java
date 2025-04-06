package com.example.healthcare.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String medicineName;
    private int dosageMg;
    private String medicineType;
    private int intervalHours;
    private String startTime;
    @ElementCollection
    private List<String> days;

    public Medicine() {
    }

    public Medicine(Long id, String medicineName, int dosageMg, String medicineType, int intervalHours, String startTime, List<String> days) {
        this.id = id;
        this.medicineName = medicineName;
        this.dosageMg = dosageMg;
        this.medicineType = medicineType;
        this.intervalHours = intervalHours;
        this.startTime = startTime;
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getDosageMg() {
        return dosageMg;
    }

    public void setDosageMg(int dosageMg) {
        this.dosageMg = dosageMg;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public int getIntervalHours() {
        return intervalHours;
    }

    public void setIntervalHours(int intervalHours) {
        this.intervalHours = intervalHours;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}
