import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import firstImg from '/images/first.png';
import secondImg from '/images/second.png';
import thirdImg from '/images/third.png';
import './Mainpage.css'

export default function MainPage() {
    const navigate = useNavigate();

    const containerVariants = {
        hidden: { opacity: 0 },
        visible: {
            opacity: 1,
            transition: {
                staggerChildren: 0.3,
                delayChildren: 0.2
            }
        }
    };

    const itemVariants = {
        hidden: { y: 20, opacity: 0 },
        visible: {
            y: 0,
            opacity: 1,
            transition: {
                type: 'spring',
                stiffness: 100,
                damping: 10
            }
        },
        hover: {
            scale: 1.03,
            transition: { duration: 0.3 }
        }
    };

    return (
        <div className="healthcare-portal">
            <nav className="portal-navbar">
                <h1>HealthCare</h1>
            </nav>

            <motion.div
                className="portal-container"
                variants={containerVariants}
                initial="hidden"
                animate="visible"
            >
                <motion.div 
                    className="welcome-section"
                    variants={itemVariants}
                >
                    <h2>Your Smart Health Companion</h2>
                    <p>AI-powered healthcare solutions at your fingertips</p>
                </motion.div>

                <motion.div 
                    className="feature-row left-image"
                    variants={itemVariants}
                >
                    <motion.img 
                        src={firstImg} 
                        alt="Medicine reminder" 
                        className="feature-image"
                        whileHover={{ scale: 1.03 }}
                    />
                    <div className="feature-content">
                        <h3>Never Forget Your Medication</h3>
                        <p>Get automated phone call reminders for your medicines at the perfect time.</p>
                        <motion.button
                            className="btn btn-primary"
                            onClick={() => navigate("/medicines")}
                            whileHover={{ scale: 1.05 }}
                            whileTap={{ scale: 0.98 }}
                        >
                            Medicine Reminder
                        </motion.button>
                    </div>
                </motion.div>

                <motion.div 
                    className="feature-row right-image"
                    variants={itemVariants}
                >
                    <div className="feature-content">
                        <h3>Understand Your Health Risks</h3>
                        <p>Upload medical reports to get AI-powered analysis of disease severity.</p>
                        <motion.button
                            className="btn btn-success"
                            onClick={() => navigate("/disease-prediction")}
                            whileHover={{ scale: 1.05 }}
                            whileTap={{ scale: 0.98 }}
                        >
                            Disease Prediction
                        </motion.button>
                    </div>
                    <motion.img 
                        src={secondImg} 
                        alt="Disease prediction" 
                        className="feature-image"
                        whileHover={{ scale: 1.03 }}
                    />
                </motion.div>

                <motion.div 
                    className="feature-row left-image"
                    variants={itemVariants}
                >
                    <motion.img 
                        src={thirdImg} 
                        alt="Voice assistant" 
                        className="feature-image"
                        whileHover={{ scale: 1.03 }}
                    />
                    <div className="feature-content">
                        <h3>Voice-Enabled Health Assistant</h3>
                        <p>Describe your symptoms naturally and get instant health guidance.</p>
                        <motion.button
                            className="btn btn-info"
                            onClick={() => navigate("/voice-assistant")}
                            whileHover={{ scale: 1.05 }}
                            whileTap={{ scale: 0.98 }}
                        >
                            Voice Assistant
                        </motion.button>
                    </div>
                </motion.div>
            </motion.div>
        </div>
    );
}