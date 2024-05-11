"use server";
import { cookies } from "next/headers";

async function ComputerCompany() {
  const token = cookies().get("token");

  if (!token) {
    throw new Error("Token not found in cookies");
  }

  try {
    const response = await fetch("http://localhost:8080/v1/computer", {
      method: "GET",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer${token}`,
      },
    });

    console.log(response);
  } catch (error) {
    console.log(error);
  }
}

export default ComputerCompany;
