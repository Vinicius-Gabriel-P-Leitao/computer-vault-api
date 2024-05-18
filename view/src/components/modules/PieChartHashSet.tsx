import { PieChart } from "@mui/x-charts";

interface PieChartHashSetProps {
  data: Set<number>; // INFO: Tem que ser usado um HashSet para que o gráfico não fique com valores repetidos
}

const PieChartHashSet = (props: PieChartHashSetProps) => {
  const { data } = props;

  return (
    <PieChart
      series={[
        {
          data: Array.from(data).map((value) => ({
            value,
            label: value.toString() + " GB",
          })),
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

export default PieChartHashSet;
