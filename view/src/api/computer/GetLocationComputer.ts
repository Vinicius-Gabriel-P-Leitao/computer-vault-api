"use server";
import makeRequest from "@/service/MakeRequest";

export default async function GetDataComputerLocation() {
  try {
    const jsonResponse = await makeRequest(
      "http://localhost:8080/v1/computer/location",
      "GET"
    );

    return { status: true, result: jsonResponse };
  } catch (error: any) {
    return { operation: false, message: error.message };
  }
}
