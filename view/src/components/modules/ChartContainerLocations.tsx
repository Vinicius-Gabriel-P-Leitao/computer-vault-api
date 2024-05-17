import {
  BarPlot,
  ChartContainer,
  ChartsXAxis,
  LinePlot,
  MarkPlot,
} from "@mui/x-charts";

interface ChartContainerLocationsProps {
  matriz?: number;
  estoque?: number;
  posto?: number;
}

const ChartContainerLocations = (props: ChartContainerLocationsProps) => {
  const { matriz = 0 } = props;
  const { estoque = 0 } = props;
  const { posto = 0 } = props;

  return (
    <ChartContainer
      width={400}
      height={400}
      series={[
        {
          type: "bar",
          data: [matriz, estoque, posto],
        },
      ]}
      xAxis={[
        {
          data: ["matriz", "estoque", "posto"],
          scaleType: "band",
          id: "x-axis-id",
        },
      ]}
    >
      <BarPlot />
      <LinePlot />
      <MarkPlot />
      <ChartsXAxis position="bottom" axisId="x-axis-id" />
    </ChartContainer>
  );
};

export default ChartContainerLocations;
