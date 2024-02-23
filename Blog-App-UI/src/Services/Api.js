import axios from "axios";
import { getToken } from "../Auth/index1";
export const API_URL = "http://localhost:8090/api/v1/";

export const myAxios =  axios.create({
baseURL: API_URL,
});


export const privateAxios =  axios.create({
    baseURL: API_URL,
    });

  privateAxios.interceptors.request.use(config=>{
    const token = getToken();
   console.log(token);
    if(token){
        config.headers.Authorization=`Bearer ${token}`
        return config
    }
  },error=>Promise.reject(error))  