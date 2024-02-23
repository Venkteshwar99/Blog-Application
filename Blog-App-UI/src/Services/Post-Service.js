import { myAxios, privateAxios } from "./Api";

export const createPost = (postData) => {
  console.log(postData);
  return privateAxios
    .post(
      `/user/${postData.userId}/category/${postData.categoryId}/posts`,
      postData
    )
    .then((response) => response.data);
};

export const loadAllPosts = (pageNumber, pageSize) => {
  return myAxios
    .get(`/posts/findAll?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=date&sortDir=desc`)
    .then((response) => response.data);
};

export const loadPost=(postId)=>{
    return myAxios
    .get("/post/"+postId)
    .then((response) => response.data);
}