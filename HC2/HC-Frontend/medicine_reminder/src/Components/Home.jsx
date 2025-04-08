import { useNavigate } from 'react-router-dom';
import home2 from '/images/home2.png';
import './Home.css'; 

export default function Home() {
    const navigate = useNavigate();

    return (
        <div className="home-container">
            <div className="image-container">
                <img src={home2} alt="Healthcare illustration" />
            </div>
            <div className="content-container">
                <div className="content-wrapper">
                    <h1>Welcome to HealthCare!</h1>
                    <p className="subtitle">Your personal medication management system</p>
                    <div className="button-group">
                        <button 
                            className="home-button register-btn"
                            onClick={() => navigate('/register')}
                        >
                            Get Started
                        </button>
                        <button 
                            className="home-button login-btn"
                            onClick={() => navigate('/login')}
                        >
                            Sign In
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}