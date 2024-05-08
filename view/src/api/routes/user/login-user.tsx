import axios from "axios";

export default function LoginUser(username: string, password: string) {
  const data = {
    username: username,
    password: password,
  };

  const headers = {
    "Content-Type": "application/json",
    username: data.username,
    password: data.password,
  };

  axios
    .post("http://localhost:8080/login", data, { headers })
    .then((response) => {
      console.log(response); // TODO: Adiciona o salvamento do token
    })
    .catch((error) => {
      console.log(error); // TODO: Melhorar tratamento de erros
    });
}
