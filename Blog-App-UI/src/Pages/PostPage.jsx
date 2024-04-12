import React, { useEffect, useState } from "react";
import Base from "../Components/Base";
import { Link, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardText, Col, Container, Input, Row } from "reactstrap";
import { createComments, loadPost } from "../Services/Post-Service";
import { toast } from "react-toastify";
import { API_URL } from "../Services/Api";

const PostPage = () => {
  const { postId } = useParams();
  const [post, setPost] = useState(null);
  const [comment, setcomment] = useState({content:''});
  useEffect(() => {
    loadPost(postId)
      .then((data) => {
        console.log(data);
        setPost(data);
      })
      .catch((error) => {
        console.log(error);
        toast.error(error);
      });
  }, []);

  const submitPost=()=>{
    createComments(comment,post.postId).then(data => {
      console.log(data);
      toast.success('Comment Added Successfully')
      setPost({...post,comments:[...post.comments,data.data]})
      setcomment({
        content:''
      })
    }).catch((error) => {
      console.log(error);
      toast.error('some error')
    })
  }

  return (
    <Base>
      <Container className="mt-4">
        <Link to={"/"}>Home</Link> /{post && <Link to="">{post.title}</Link>}
        <Row>
          <Col md={{ size: 12 }}>
            <Card className="mt-4 ps-2 shadow">
              {post && (
                <CardBody>
                  <CardText>
                    Posted By: <b>{post.user.name}</b> on{" "}
                    <b> {new Date(post.date).toLocaleString()}</b>
                  </CardText>

                  <CardText>
                    <span className="text-muted">
                      {post.category.categoryTitle}
                    </span>
                  </CardText>
                  <div
                    className="divider"
                    style={{
                      width: "100%",
                      height: "1px",
                      background: "#e2e2e2",
                    }}
                  ></div>

                  <CardText>
                    <b>{post.title}</b>
                  </CardText>
                  <div className="image-container container mt-4 ">
                    <img
                      src={API_URL + "post/image/" + post.imageName}
                      alt={post.title}
                      style={{ maxWidth: "50%" }}
                    />
                  </div>
                  <CardText
                    className="mt-4"
                    dangerouslySetInnerHTML={{ __html: post.content }}
                  ></CardText>
                </CardBody>
              )}
            </Card>
          </Col>
        </Row>
        <Row className="mt-4">
          <Col md={{ size: 11, offset: 0 }}>
            {" "}
            <h3>Comments({post ? post.comments.length : 0})</h3>
            {post &&
              post.comments.map((c, index) => (
                <Card className="my-2"key={index}>
                  <CardBody>
                    <CardText>{c.c}</CardText>
                  </CardBody>
                </Card>
              ))}
                <Card >
                  <CardBody>
                    <Input type="text-area" placeholder="Enter Comment Here" 
                    value={comment.comment}
                    onChange={(event)=> setcomment({content:event.target.value})}></Input>
                    <Button onClick={submitPost} className="mt-4" color="primary">Submit</Button>
                  </CardBody>
                </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default PostPage;
