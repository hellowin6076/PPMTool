import { GET_ERRORS } from "./types";

export const clearErrors = () => async (dispatch) => {
  dispatch({
    type: GET_ERRORS,
    payload: {},
  });
};
