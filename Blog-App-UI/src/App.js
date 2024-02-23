import "./App.css";
import Base from "./Components/Base";
import About from "./Pages/About";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import Login from "./Pages/Login";
import SignUp from "./Pages/SignUp";
import Services from "./Pages/Services";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import UserDashboard from "./Pages/User-Routes/UserDashboard";
import PrivateRoute from "./Components/PrivateRoute";
import ProfileInfo from "./Pages/User-Routes/ProfileInfo";
import PostPage from "./Pages/PostPage";

function App() {
  return (
    <>
      <Router>
        <ToastContainer position="bottom-left" autoClose={2000} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/about" element={<About />} />
          <Route path="/base" element={<Base />} />
          <Route path="/services" element={<Services />} />
          <Route path="/post/:postId" element={<PostPage />} />

          <Route path="/user" element={<PrivateRoute />}>
            <Route path="dashboard" element={<UserDashboard />} />
            <Route path="profileInfo" element={<ProfileInfo />} />
          </Route>
        </Routes>
      </Router>
    </>
  );
}

export default App;
