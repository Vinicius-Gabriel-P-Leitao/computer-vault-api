"use client";
import ComputerCompany from "@/api/computer/computer-company";
import { useState } from "react";

export default function Dashboard() {
  const [data, setData] = useState<any[]>([]);

  const handleClick = async () => {
    const dataResponse = await ComputerCompany();
    console.log(dataResponse.result);
    setData(dataResponse.result);
  };

  return (
    <div>
      Dash
      <button onClick={handleClick}>Click</button>
      <div>
        {data.length > 0 ? (
          // Se 'data' não estiver vazio, mapeia os itens para renderização
          data.map((item: ComputadorData, index) => (
            // Renderiza cada item com seu 'identificador' como conteúdo
            <div key={index}>{item["dados-gerais"].local}</div>
          ))
        ) : (
          // Se 'data' estiver vazio, mostra uma mensagem indicando que não há dados
          <p>
            Nenhum dado disponível. Clique no botão acima para carregar os
            dados.
          </p>
        )}
      </div>
    </div>
  );
}
