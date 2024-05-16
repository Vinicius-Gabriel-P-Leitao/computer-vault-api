"use server";
import makeRequest from "@/service/MakeRequest";

async function GetDataComputer(url: string) {
  try {
    const jsonResponse = await makeRequest(url, "GET");

    return { status: true, result: jsonResponse };
  } catch (error: any) {
    return { status: false, result: error.message };
  }
}

async function SetDataComputer(
  url: string,
  data: ComputerData,
  options: "POST" | "PATCH",
  id?: number
) {
  try {
    const method = options === "POST" ? "POST" : "PATCH";
    const jsonResponse = await makeRequest(url, method, data, id);

    return { status: true, result: jsonResponse };
  } catch (error: any) {
    return { status: false, result: error.message };
  }
}

async function DeleteDataComputer(url: string, id: number) {
  try {
    const jsonResponse = await makeRequest(url, "DELETE", undefined, id);

    return { status: true, result: jsonResponse };
  } catch (error: any) {
    return { status: false, result: error.message };
  }
}

export { DeleteDataComputer, GetDataComputer, SetDataComputer };

