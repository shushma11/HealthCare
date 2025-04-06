import axios from "axios";
import { useEffect, useState } from "react";
import './AllMedicines.css';
import { useNavigate } from "react-router-dom";

export default function AllMedicines() {
    const [medicines, setMedicines] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        getAllMedicines();
    }, []); 

    function getAllMedicines() {
        setLoading(true);
        setError(null);
        axios.get("http://localhost:8080/api/healthcare/all")
            .then((response) => {
                setMedicines(response.data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("Error fetching medicines:", error);
                setError("Failed to load medicines. Please try again later.");
                setLoading(false);
            });
    }

    function formatDisplayDate(dateString) {
        if (!dateString) return "Not specified";
        
        try {
            const [datePart, timePart] = dateString.split(' ');
            const [day, month, year] = datePart.split('-');
            const [time, period] = timePart.split(' ');
            
            return `${day}/${month}/${year} ${time} ${period}`;
        } catch (e) {
            console.error("Error formatting date:", e);
            return dateString;
        }
    }
    function handleClick(medicine) {
        console.log("Selected Medicine:", {
            name: medicine.medicineName,
            type: medicine.medicineType,
            dosage: `${medicine.dosageMg}mg`,
            startTime: medicine.startTime,
            interval: `${medicine.intervalHours} hours`,
            days: medicine.days.join(", ")
        });
        // Navigate to the medicine form with the medicine data
        navigate("/", { state: medicine });
    }

    if (loading) {
        return <div className="loading">Loading medicines...</div>;
    }

    if (error) {
        return <div className="error">{error}</div>;
    }

    return (
        <div className="all-medicines-container">
            <h1 className="page-title">Your Medications</h1>
            <button onClick={() => navigate("/")} className="submit-btn">
                Add New Medicine
            </button>
            {medicines.length === 0 ? (
                <div className="empty-state">
                    <p>No medications found. Add your first medication to get started!</p>
                </div>
            ) : (
                <div className="medicine-grid">
                    {medicines.map((medicine, index) => (
                        <div 
                            key={index} 
                            className="medicine-card"
                            onClick={() => handleClick(medicine)}
                        >
                            <div className="medicine-header">
                                <h2 className="medicine-name">{medicine.medicineName}</h2>
                                <span className="medicine-type">{medicine.medicineType}</span>
                            </div>
                            <div className="medicine-details">
                                <p><strong>Dosage:</strong> {medicine.dosageMg}mg</p>
                                <p><strong>Starts:</strong> {formatDisplayDate(medicine.startTime)}</p>
                                <p><strong>Interval:</strong> Every {medicine.intervalHours} hours</p>
                                {medicine.days?.length > 0 && (
                                    <p><strong>Days:</strong> {medicine.days.join(", ")}</p>
                                )}
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}