import { myAxios } from "./Api";

export const loadAllCategories = () => {
  return myAxios.get(`/category/findAll`).then((response) => response.data);
};
