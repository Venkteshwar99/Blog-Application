import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.css";
import {
  Collapse,
  DropdownItem,
  DropdownMenu,
  DropdownToggle,
  Nav,
  NavItem,
  NavLink,
  Navbar,
  NavbarBrand,
  NavbarToggler,
  UncontrolledDropdown,
} from "reactstrap";
import { Link, useNavigate } from "react-router-dom";
import { doLogout, getCurrentUser, isLoggedIn } from "../Auth/index1";

// Main Code
const CustomNavBar = () => {
  const [collapsed, setCollapsed] = useState(true);
  const toggleNavbar = () => setCollapsed(!collapsed);
  const [login, setLogin] = useState(false);
  const [user, setUser] = useState(undefined);
  const navigate = useNavigate();
  useEffect(() => {
    setLogin(isLoggedIn());
    setUser(getCurrentUser());
  }, [login]);

  const logout =()=>{
    doLogout(()=>{
     setLogin(false);
     navigate("/")
    })
  }

  return (
    <Navbar color="dark" dark expand="md" fixed="" className="px-4">
      <NavbarBrand tag={Link} to="/">
        MyBlogs
      </NavbarBrand>
      <NavbarToggler onClick={toggleNavbar} />
      <Collapse navbar isOpen={!collapsed}>
        <Nav className="me-auto" navbar>
          <NavItem>
            <NavLink tag={Link} to="/">
              New Feeds
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink tag={Link} to="/about">
              About
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink tag={Link} to="/services">
              Services
            </NavLink>
          </NavItem>
          <UncontrolledDropdown inNavbar nav>
            <DropdownToggle caret nav>
              More
            </DropdownToggle>
            <DropdownMenu end>
              <DropdownItem>Contact Us</DropdownItem>
              <DropdownItem divider />
              <DropdownItem>Reset</DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
        </Nav>

        <Nav navbar>
          {/* Checking Below If user if LoggedIn then show Logout Option & User Email*/}
          {login && (
            <>
             <NavItem>
                <NavLink  tag={Link} to="/user/profileInfo">Profile Info</NavLink>
              </NavItem>

              <NavItem>
                <NavLink tag={Link} to="/user/dashboard">{user.email}</NavLink>
              </NavItem>

              <NavItem>
                <NavLink onClick={logout}>Logout</NavLink>
              </NavItem>
            </>
          )}
          {/* Its Else part Below If user if not LoggedIn then show Login & SignUp Option*/}
          {!login && (
            <>
              <NavItem>
                <NavLink tag={Link} to="/login">
                  Login
                </NavLink>
              </NavItem>

              <NavItem>
                <NavLink tag={Link} to="/signup">
                  Signup
                </NavLink>
              </NavItem>
            </>
          )}
        </Nav>
      </Collapse>
    </Navbar>
  );
};

export default CustomNavBar;
