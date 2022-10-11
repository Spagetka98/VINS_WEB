import axios from "axios";

const Client = axios.create({
  baseURL: "http://localhost:9001/",
  headers: {
    "Content-Type": "application/json",
  },
});

export default Client;
