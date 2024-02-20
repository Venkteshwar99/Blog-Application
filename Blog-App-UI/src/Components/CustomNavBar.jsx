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
import { Link } from "react-router-dom";

// Main Code
const CustomNavBar = () => {
  const [collapsed, setCollapsed] = useState(true);
  const toggleNavbar = () => setCollapsed(!collapsed);
  
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
        </Nav>
      </Collapse>
    </Navbar>
  );
};

export default CustomNavBar;
