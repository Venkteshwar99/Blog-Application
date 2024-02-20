import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormFeedback,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";
import Base from "../Components/Base";
import { useEffect, useState } from "react";
import { signup } from "../Services/User-Service";
import { toast } from "react-toastify";

const SignUp = () => {
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
    about: "",
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  const handleChange = (e, property) => {
    setData({ ...data, [property]: e.target.value });
  };

  useEffect(() => {
    console.log(data);
  }, [data]);

  const resetData = () => {
    setData({
      name: "",
      email: "",
      password: "",
      about: "",
    });
  };

  const submitForm = (e) => {
    e.preventDefault();

    if (error.isError) {
      toast.error("Form data is invalid, correct all details then submit.");
      setError({ ...error, isError: false });
      return;
    }
    console.log(data);
    signup(data)
      .then((resp) => {
        console.log(resp);
        console.log("success");
        toast.success("User is registered successfully!! User Id:" + resp.id);
        setData({
          name: "",
          email: "",
          password: "",
          about: "",
        });
      })
      .catch((error) => {
        console.log(error);
        setError({
          errors: error,
          isError: true,
        });
      });
  };

  return (
    <Base>
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card outline color="dark">
              <CardHeader>
                <h3>User Registration Form</h3>
              </CardHeader>

              <CardBody>
                <Form onSubmit={submitForm}>
                  {/*Name Property */}
                  <FormGroup>
                    <Label for="name">Enter Name</Label>
                    <Input
                      type="text"
                      placeholder="Enter Here"
                      id="name"
                      onChange={(e) => handleChange(e, "name")}
                      value={data.name}
                      invalid={
                        error.errors?.response?.data?.name ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.name}
                    </FormFeedback>
                  </FormGroup>

                  {/*Email Property */}
                  <FormGroup>
                    <Label for="email">Enter Email</Label>
                    <Input
                      type="text"
                      placeholder="Enter Here"
                      id="email"
                      onChange={(e) => handleChange(e, "email")}
                      value={data.email}
                      invalid={
                        error.errors?.response?.data?.email ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.email}
                    </FormFeedback>
                  </FormGroup>

                  {/*Password Property */}
                  <FormGroup>
                    <Label for="password">Enter Password</Label>
                    <Input
                      type="text"
                      placeholder="Enter Here"
                      id="password"
                      onChange={(e) => handleChange(e, "password")}
                      value={data.password}
                      invalid={
                        error.errors?.response?.data?.password ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.password}
                    </FormFeedback>
                  </FormGroup>

                  {/*About Property */}
                  <FormGroup>
                    <Label for="about">About</Label>
                    <Input
                      type="text"
                      placeholder="Enter Here"
                      id="about"
                      style={{ height: "150px" }}
                      onChange={(e) => handleChange(e, "about")}
                      value={data.about}
                    />
                  </FormGroup>

                  <Container className="text-center">
                    <Button outline color="dark">
                      Register
                    </Button>
                    <Button
                      outline
                      color="dark"
                      type="reset"
                      className="ms-2"
                      onClick={resetData}
                    >
                      Reset
                    </Button>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default SignUp;
