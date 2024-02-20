
import './App.css';
import Base from './Components/Base';
import About from './Pages/About';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from './Pages/Home';
import Login from './Pages/Login';
import SignUp from './Pages/SignUp';
import Services from './Pages/Services';




function App() {
  return (
  <>
   <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/about" element={<About />} />
        <Route path="/base" element={<Base />} />
        <Route path="/services" element={<Services />} />
      </Routes>
    </Router>
    </>
  );
}

export default App;
