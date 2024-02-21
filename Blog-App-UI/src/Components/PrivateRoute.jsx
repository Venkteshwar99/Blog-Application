import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { isLoggedIn } from "../Auth/index1";

const PrivateRoute = () => {
  return isLoggedIn() ? <Outlet /> : <Navigate to={"/login"} />;
};

export default PrivateRoute;
