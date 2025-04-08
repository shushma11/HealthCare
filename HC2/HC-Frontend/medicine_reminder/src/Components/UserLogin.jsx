import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import "./User.css";

export default function UserLogin() {
    const [number, setNumber] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(
                "http://localhost:8080/api/auth/login",
                {
                    phoneNumber: number,
                    password: password,
                },
                {
                    headers: {
                        "Content-Type": "application/json",
                    },
                }
            );

            const respD = response.data;
            if (respD.message === "User exists") {
                const userId = respD.userId;
                localStorage.setItem("userId", userId);
                navigate("/mainpage");
            } else {
                navigate("/register");
            }
        } catch (error) {
            console.error("Login failed:", error);
            setError("Login failed. Check credentials or try later.");
        }
    };

    return (
        <div className="auth-wrapper" >
            <div className="auth-card">
                <div className="auth-header">
                    <h1>Welcome Back</h1>
                    <p>Enter your details to log in</p>
                </div>

                {error && <div className="error-message">{error}</div>}

                <form onSubmit={handleSubmit} className="auth-form">
                    <div className="form-group">
                        <label htmlFor="phone">Phone Number</label>
                        <input
                            type="tel"
                            id="phone"
                            className="form-input"
                            value={number}
                            onChange={(e) => setNumber(e.target.value)}
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            id="password"
                            className="form-input"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>

                    <button type="submit" className="auth-button">Login</button>
                </form>

                <div className="auth-footer">
                    Don't have an account? <span className="auth-link" onClick={() => navigate('/register')}>Register</span>
                </div>
            </div>
        </div>
    );
}





// const handleSubmit = (e) => {
//     e.preventDefault();
    
//     axios.post("http://localhost:8080/api/auth/login", {
//         phoneNumber: number,
//         password: password
//     })
//     .then((response) => {
//         const respD = response.data;
    
//         if (respD.message === "User exists") {
//             const userId = respD.userId;
//             alert("User id sen tby bakcend is: ",userId);
//             localStorage.setItem("userId", userId);
    
//             navigate("/mainpage");
//         } else {
//             navigate("/register");
//         }
//     });
// };