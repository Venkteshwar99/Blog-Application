//isLoggedIn
export const isLoggedIn = () => {
  let data = localStorage.getItem("data");
  return data == null ? false : true;

  // if(data===null){
  //     return false;
  // }else{
  //     return true;
  // }
};

//do login
export const doLogin = (data, next) => {
  localStorage.setItem("data", JSON.stringify(data));
  next();
};

//doLogout
export const doLogout = (next) => {
  localStorage.removeItem("data");
  next();
};

//getCurrentUser
export const getCurrentUser = () => {
  if (isLoggedIn()) {
    return JSON.parse(localStorage.getItem("data")).user;
  } else {
    return undefined;
  }
};

export const getToken = () => {
  if (isLoggedIn()) {
    return JSON.parse(localStorage.getItem("data")).jwtToken;
  } else {
    return null;
  }
};
