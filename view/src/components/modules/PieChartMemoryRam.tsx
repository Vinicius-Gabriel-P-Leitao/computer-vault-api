import { PieChart } from "@mui/x-charts";

interface PieChartHashSetProps {
  data: ComputerMemoryRam | undefined; // INFO: Tem que ser usado um HashSet para que o gráfico não fique com valores repetidos
}

const PieChartMemoryRam = (props: PieChartHashSetProps) => {
  const { data } = props;

  const chartData = data
    ? Object.entries(data).map(([key, value]) => ({
        label: `${key} GB`,
        value: value,
      }))
    : [];

  return (
    <PieChart
      width={400}
      height={300}
      series={[
        {
          data: chartData,
          innerRadius: 50,
          outerRadius: 100,
          paddingAngle: 5,
          cornerRadius: 5,
          startAngle: -90,
          endAngle: 180,
          cx: 150,
          cy: 150,
        },
      ]}
    />
  );
};

export default PieChartMemoryRam;
