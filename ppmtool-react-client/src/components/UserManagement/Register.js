import React, { Component } from "react";

class Register extends Component {
  render() {
    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h1 className="display-4 text-center">Sign Up</h1>
              <p className="lead text-center">Create your Account</p>
              <form action="create-profile.html">
                <div className="mb-3">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Name"
                    name="name"
                    required
                  />
                </div>
                <div className="mb-3">
                  <input
                    type="email"
                    className="form-control form-control-lg"
                    placeholder="Email Address"
                    name="email"
                  />
                </div>
                <div className="mb-3">
                  <input
                    type="new-password"
                    className="form-control form-control-lg"
                    placeholder="Password"
                    name="password"
                  />
                </div>
                <div className="mb-3">
                  <input
                    type="new-password"
                    className="form-control form-control-lg"
                    placeholder="Confirm Password"
                    name="password2"
                  />
                </div>
                <div className="d-grid">
                  <input
                    type="submit"
                    className="btn btn-info btn-block mt-4"
                  />
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Register;
