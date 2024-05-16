"use server";
import { cookies } from "next/headers";

async function makeRequest(
  url: string,
  method: string,
  data?: any,
  id?: number
) {
  const requestUrl = id ? `${url}/${id}` : url;
  const cookieStore = cookies();
  const token = cookieStore.get("token");

  const response = await fetch(requestUrl, {
    method: method,
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token?.value}`,
    },
    body: data ? JSON.stringify(data) : undefined,
  });

  const jsonResponse = await response.json();

  if (!response.ok) {
    throw new Error(jsonResponse.message || "Erro ao realizar requisição");
  }

  return jsonResponse;
}

export default makeRequest;
