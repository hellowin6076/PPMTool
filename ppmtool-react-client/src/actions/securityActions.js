import axios from "axios";
import setJWTToken from "../securityUtils/setJWTToken";
import { GET_ERRORS } from "./types";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async (dispatch) => {
  try {
    await axios.post("/api/users/register", newUser);
    history.push("/login");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const login = (LoginRequest) => async (dispatch) => {
  try {
    // post => Login Request
    const res = await axios.post("/api/users/login", LoginRequest);
    // extratct token for res.data
    const { token } = res.data;
    // store the token in the localStorage
    localStorage.setItem("jwtToken", token);
    // set oure token in header ***
    setJWTToken(token);
    // decode token on React
    const decoded = jwt_decode(token);
    // dispatch to oure securityReducer
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded,
    });
  } catch (err) {
    //dispatch({
    //  type: GET_ERRORS,
    //  payload: err.response.data,
    // });
  }
};
