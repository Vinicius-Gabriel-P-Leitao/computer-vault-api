import { BarChart } from "@mui/x-charts";

interface BarChartStorageComputerProps {
  dataStorage: { hd: Record<string, number>; ssd: Record<string, number> };
}

const BarChartStorageComputer = (state: BarChartStorageComputerProps) => {
  /* 
    Recebe o tipo de armazenamento e retorna um objeto com os dados do armazenamento.
    Retorna um objeto com os valores mapeados da requisição
  */
  const extractDataStorage = (type: "hd" | "ssd") => {
    const typesArmazenamento = Object.keys((state.dataStorage?.hd || {}) ?? {});
    
    const data = typesArmazenamento.map(
      (size) => state.dataStorage?.[type]?.[size] || 0
    );

    return { data: data };
  };

  return (
    <BarChart
      xAxis={[
        {
          scaleType: "band",
          data: Object.keys(state.dataStorage?.hd || {}).map(
            (size) => `${size} GB`
          ),
        },
      ]}
      series={[extractDataStorage("hd"), extractDataStorage("ssd")]}
      width={500}
      height={300}
    />
  );
};

export default BarChartStorageComputer;
