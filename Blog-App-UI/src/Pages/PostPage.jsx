import React, { useEffect, useState } from "react";
import Base from "../Components/Base";
import { Link, useParams } from "react-router-dom";
import { Card, CardBody, CardText, Col, Container, Row } from "reactstrap";
import { loadPost } from "../Services/Post-Service";
import { toast } from "react-toastify";
import { API_URL } from "../Services/Api";

const PostPage = () => {
  const { postId } = useParams();
  const [post, setPost] = useState(null);
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

                  <CardText><b>{post.title}</b></CardText>
                  <div
                    className="image-container container mt-4 "
                    
                  >
                    <img
                      src={API_URL + "post/image/" + post.imageName}
                      alt={post.title}
                      style={{ maxWidth:"50%" }}/>
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
      </Container>
    </Base>
  );
};

export default PostPage;
