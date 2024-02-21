import { myAxios } from "./Api.js";

export const signup = (user) => {
  return myAxios
    .post("/auth/register", user)
    .then((response) => response.data);
};

export const loginUser = (loginDetails) => {
  return myAxios
    .post("/auth/login", loginDetails)
    .then((response) => response.data);
};
