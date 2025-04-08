import { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import "./User.css";

export default function UserRegister() {
    const [name, setName] = useState('');
    const [number, setNumber] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        
        const userData = {
            name: name,
            phoneNumber: number,
            password: password
        };

        axios.post("http://localhost:8080/api/auth/register", userData)
            .then((response) => {
                console.log(response.data);
                alert("Registration successful!");
                navigate("/login");
            })
            .catch((error) => {
                console.error(error);
                setError(error.response?.data?.message || "Registration failed. Please try again.");
            });
    };

    return (
        <div className="auth-page">
            <div className="auth-card">
                <div className="auth-header">
                    <h1>Create Account</h1>
                    <p>Join us to manage your medications</p>
                </div>
                
                {error && <div className="error-message">{error}</div>}
                
                <form onSubmit={handleSubmit} className="auth-form">
                    <div className="form-group">
                        <input
                            type="text"
                            placeholder="Full Name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                            className="form-input"
                        />
                    </div>
                    <div className="form-group">
                        <input
                            type="tel"
                            placeholder="Phone Number"
                            value={number}
                            onChange={(e) => setNumber(e.target.value)}
                            required
                            className="form-input"
                        />
                    </div>
                    <div className="form-group">
                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            className="form-input"
                        />
                    </div>
                    <button type="submit" className="auth-button">Register</button>
                </form>
                
                <div className="auth-footer">
                    <p>Already have an account? <span onClick={() => navigate('/login')} className="auth-link">Sign In</span></p>
                </div>
            </div>
        </div>
    );
}