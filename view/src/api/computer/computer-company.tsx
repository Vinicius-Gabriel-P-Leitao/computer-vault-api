"use server";
import { cookies } from "next/headers";

async function ComputerCompany() {
  const cookieStore = cookies();
  const token = cookieStore.get("token");

  if (!token) {
    throw new Error("Token not found in cookies");
  }

  try {
    const response = await fetch("http://localhost:8080/v1/computer", {
      method: "GET",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token.value}`,
      },
    });

    if (!response.ok) {
      return { operation: false, message: "Erro ao realizar requisição" };
    }

    const jsonResponse = await response.json();

    return { status: true, result: jsonResponse };
  } catch (error) {
    return { operation: false, message: "Erro interno ao realizar requisição" };
  }
}

export default ComputerCompany;
