// import './App.css'
import Medicine from "./Components/Medicine"
import AllMedicines from "./Components/AllMedicines";
import {BrowserRouter,Routes,Route} from 'react-router-dom';
function App() {
  return(
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/all" element={<AllMedicines/>}></Route>
        <Route path="/" element={<Medicine/>}></Route>
        <Route path="/medicine/:id" element={<Medicine/>}></Route> {/* Add this route */}
      </Routes>
    </BrowserRouter>
    </>
  );
}

export default App
