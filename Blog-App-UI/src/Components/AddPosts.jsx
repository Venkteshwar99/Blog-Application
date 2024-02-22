import React, { useEffect, useRef, useState } from "react";
import {
  Button,
  Card,
  CardBody,
  Container,
  Form,
  Input,
  Label,
} from "reactstrap";
import { loadAllCategories } from "../Services/Category-Service";
import JoditEditor from "jodit-react";
import { createPost as doCreatePost } from "../Services/Post-Service";
import { getCurrentUser } from "../Auth/index1";
import { toast } from "react-toastify";

const AddPosts = () => {
  const [categories, setCategories] = useState([]);
  const editor = useRef(null);
  const [user, setUser] = useState(undefined);

  const [post, setPost] = useState({
    title: "",
    content: "",
    categoryId: "",
  });

  useEffect(() => {
    setUser(getCurrentUser());
    loadAllCategories()
      .then((data) => {
        console.log(data);
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const fieldChanged = (event) => {
    //console.log(event.target.name);
    setPost({ ...post, [event.target.name]: event.target.value });
  };
  const contentFieldChanged = (data) => {
    setPost({ ...post, content: data });
  };

  const createPost = (event) => {
    event.preventDefault();
    console.log(post);

    if (post.title.trim() === "") {
      toast.error("Post Title Is Required!!");
      return;
    }

    if (post.content.trim() === "") {
      toast.error("Post Content Is Required!!");
      return;
    }

    if (post.categoryId === "") {
      toast.error("Select Category!!");
      return;
    }

    post["userId"] = user.id;
    //submit the form on server
    doCreatePost(post)
      .then((data) => {
        toast.success("post created");
        //console.log(post);
        setPost({
          title: "",
          content: "",
          categoryId: "",
        });
      })
      .catch((error) => {
        // console.log(error);
        toast.error("error occurred!!");
      });
  };

  return (
    <div className="wrapper p-2">
      <Card className="shadow-sm">
        <CardBody>
          <h3>What's going on your mind</h3>
          <Form onSubmit={createPost}>
            <div className="my-3">
              <Label for="title">Post Title</Label>
              <Input
                type="text"
                id="title"
                placeholder="Enter here"
                className="rounded-0"
                onChange={fieldChanged}
                name="title"
              />
            </div>

            <div className="my-3">
              <Label for="content">Post Content</Label>
              {/* <Input
                type="text"
                id="content"
                placeholder="Enter here"
                className="rounded-0"
                style={{ height: "200px" }}
              /> */}

              <JoditEditor
                ref={editor}
                value={post.content}
                onChange={(newContent) => contentFieldChanged(newContent)} // preferred to use only this option to update the content for performance reasons
              />
            </div>

            <div className="my-3">
              <Label for="category">Post Category</Label>
              <Input
                type="select"
                id="category"
                placeholder="Enter here"
                className="rounded-0"
                name="categoryId"
                onChange={fieldChanged}
                defaultValue={0}
              >
                <option disabled value={0}>
                  --Select Category--
                </option>
                {categories.map((category) => (
                  <option value={category.categoryId}>
                    {category.categoryTitle}
                  </option>
                ))}
              </Input>
            </div>

            <Container className="text-center">
              <Button type="submit" className="rounded-0" color="primary">
                Create Post
              </Button>
              <Button type="" className="rounded-0 ms-2" color="danger">
                Reset Content
              </Button>
            </Container>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};

export default AddPosts;
