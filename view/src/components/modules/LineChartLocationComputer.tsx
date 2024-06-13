import {
  BarPlot,
  ChartsXAxis,
  LineChart,
  LinePlot,
  MarkPlot,
} from "@mui/x-charts";

interface ChartContainerLocationsProps {
  matriz?: number;
  estoque?: number;
  posto?: number;
}

const LineChartLocationComputer = (props: ChartContainerLocationsProps) => {
  const { matriz = 0 } = props;
  const { estoque = 0 } = props;
  const { posto = 0 } = props;

  return (
    <LineChart
      series={[
        {
          data: [matriz, estoque, posto],
          label: "Quantidade por local",
          area: true,
        },
      ]}
      xAxis={[
        {
          data: ["matriz", "estoque", "posto"],
          scaleType: "band",
          id: "x-axis-id",
        },
      ]}
      width={300}
      height={300}
    >
      <ChartsXAxis position="bottom" axisId="x-axis-id" />
    </LineChart>
  );
};

export default LineChartLocationComputer;
