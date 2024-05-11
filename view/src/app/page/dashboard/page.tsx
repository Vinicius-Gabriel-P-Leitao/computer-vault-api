"use client"
import ComputerCompany from "@/api/computer/computer-company";

export default function Dashboard() {
  const handleClick = async () => {
    await ComputerCompany();
  };

  return (
    <div>
      Dash
      <button onClick={handleClick}>Click</button>
    </div>
  );
}
