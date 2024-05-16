"use server";
import { cookies } from "next/headers";

interface LoginResponse {
  operation: boolean;
  message: string;
}

async function LoginUser(
  username: string,
  password: string
): Promise<LoginResponse> {
  const headers: HeadersInit = {
    "Content-Type": "application/json",
    username: username,
    password: password,
  };

  try {
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: headers,
    });

    if (!response.ok) {
      return { operation: false, message: "Erro ao realizar login" };
    } else {
      const jsonData = await response.json();

      cookies().set("token", jsonData.access_token);
      // localStorage.setItem("token", jsonData.access_token);

      return { operation: true, message: "Login realizado com sucesso!" };
    }
  } catch (error) {
    return { operation: false, message: "Erro ao realizar login" };
  }
}

export default LoginUser;
