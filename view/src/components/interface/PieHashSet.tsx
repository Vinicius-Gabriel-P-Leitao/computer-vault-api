import { PieChart } from "@mui/x-charts";

interface PieHashSetProps {
  data: Set<number>; // INFO: Tem que ser usado um HashSet para que o gráfico não fique com valores repetidos
}

const PieHashSet = (props: PieHashSetProps) => {
  const { data } = props;

  return (
    <PieChart
      series={[
        {
          data: Array.from(data).map((value) => ({ value })),
          innerRadius: 30,
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

export default PieHashSet;
