import axios from "axios";
const API_URL = "http://localhost:8090/";

export const myAxios =  axios.create({
baseURL: API_URL,
});


