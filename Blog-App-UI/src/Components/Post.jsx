import React from "react";
import { Link } from "react-router-dom";
import {Card, CardBody, CardText, Container } from "reactstrap";

const Post = ({
  post = {
    title: "",
    content: "",
  },
}) => {
  return (
  
    <Card className="border-0 shadow-sm mt-4" >
      <CardBody style={{ height:"200px"}}>
        <h1>{post.title}</h1>
        <CardText
          dangerouslySetInnerHTML={{
            __html: post.content.substring(0, 40) + "....",
          }}
        ></CardText>
        <div>
          <Link className="btn btn-secondary" to={'/post/'+post.postId}>Read More</Link>
        </div>
      </CardBody>
    </Card>

  );
};

export default Post;
