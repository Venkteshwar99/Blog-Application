import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";
import Base from "../Components/Base";
import { useState } from "react";
import { toast } from "react-toastify";
import { loginUser } from "../Services/User-Service";
import { doLogin } from "../Auth/index1";
import { useNavigate } from "react-router-dom";

const Login = () => {
 
  const navigate = useNavigate();

  const [loginDetails, setLoginDetails] = useState({
    email: "",
    password: "",
  });

  const handleChange = (event, field) => {
    let actualValue = event.target.value;
    setLoginDetails({ ...loginDetails, [field]: actualValue });
  };

  const handleReset = () => {
    setLoginDetails({ email: "", password: "" });
  };
  const handleFormSubmit = (event) => {
    event.preventDefault();
    console.log(loginDetails);
    if (
      loginDetails.email.trim() === "" ||
      loginDetails.password.trim() === ""
    ) {
      toast.error("Username and Password is required!!");
      return;
    }
    //submitting data to server to generate token
    loginUser(loginDetails)
      .then((data) => {
        console.log(data);

        doLogin(data, () => {
          console.log("User LoggedIn");
         //redirect to user dashboard page
         navigate("/user/dashboard");
        });

        toast.success("User LoggedIn SuccessFully!");
      })
      .catch((error) => {
        console.log(error);
        if (error.response.status === 400 || error.response.status === 404) {
          toast.error(error.response.data.message);
        } else {
          toast.error("Something went wrong");
        }
      });
  };

  return (
    <Base>
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card>
              <CardHeader>
                <h3>Login Here</h3>
              </CardHeader>
              <CardBody>
                <Form onSubmit={handleFormSubmit}>
                  {/*Email Property */}
                  <FormGroup>
                    <Label for="email">Enter Email</Label>
                    <Input
                      type="text"
                      placeholder="Enter Here"
                      id="email"
                      onChange={(e) => handleChange(e, "email")}
                      value={loginDetails.email}
                    />
                  </FormGroup>

                  {/*Password Property */}
                  <FormGroup>
                    <Label for="password">Enter Password</Label>
                    <Input
                      type="text"
                      placeholder="Enter Here"
                      id="password"
                      onChange={(e) => handleChange(e, "password")}
                      value={loginDetails.password}
                    />
                  </FormGroup>

                  <Container className="text-center">
                    <Button outline color="dark">
                      Login
                    </Button>
                    <Button
                      outline
                      color="dark"
                      type="reset"
                      className="ms-2"
                      onClick={handleReset}
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

export default Login;
