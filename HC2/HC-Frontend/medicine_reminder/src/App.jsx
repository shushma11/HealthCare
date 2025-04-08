// import './App.css'
import Medicine from "./Components/Medicine"
import AllMedicines from "./Components/AllMedicines";
import {BrowserRouter,Routes,Route} from 'react-router-dom';
import UserRegister from "./Components/UserRegister";
import UserLogin from "./Components/UserLogin";
import Home from "./Components/Home";
import MainPage from "./Components/Mainpage";
function App() {
  return(
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/register" element={<UserRegister/>} />
        <Route path="/login" element={<UserLogin/>} />
        <Route path="/mainpage" element={<MainPage/>}/>
        <Route path="/medicine/:id" element={<Medicine />} />
        <Route path="/medicines" element={<AllMedicines/>} />
        <Route path="/add-medicine" element={<Medicine/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App


