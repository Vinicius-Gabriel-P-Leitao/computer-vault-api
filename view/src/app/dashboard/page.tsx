"use client";
import { useEffect, useState } from "react";
import { GetDataComputer } from "@/api/computer/CrudComputer";
import GetDataComputerLocation from "@/api/computer/GetLocationComputer";
import PieHashSet from "@/components/interface/PieHashSet";
import { Alert, Box, Grid, Paper } from "@mui/material";
import {
  BarPlot,
  ChartContainer,
  ChartsXAxis,
  LinePlot,
  MarkPlot,
} from "@mui/x-charts";

export default function Dashboard() {
  const [dataMemoryRam, setDataMemoryRam] = useState<Set<number>>(new Set());
  const [dataLocation, setDataLocation] = useState<ComputerLocation>();
  const [error, setError] = useState<boolean>(false);

  const dataDashboard = async () => {
    try {
      const dataComputer = await GetDataComputer(
        "http://localhost:8080/v1/computer"
      );
      const dataLocation = await GetDataComputerLocation();

      const memoryData = dataComputer.result.map(
        (item: ComputerData) => item.hardware["memoria-ram"]
      );

      setDataLocation(dataLocation.result);
      setDataMemoryRam(new Set(memoryData));
    } catch (error: any) {
      setError(true);
    }
  };

  useEffect(() => {
    dataDashboard();

    const intervalId = setInterval(() => {
      dataDashboard();
      console.log("Dashboard");
    }, 10000);

    return () => clearInterval(intervalId);
  }, []);

  const getMatriz = dataLocation?.MATRIZ ?? 0;
  const getEstoque = dataLocation?.ESTOQUE ?? 0;
  const getPosto = dataLocation?.POSTO ?? 0;

  return (
    <section>
      <Box sx={{ width: "100%" }}>
        {error ? (
          <Alert severity="error">Erro ao buscar valores</Alert>
        ) : (
          <Paper sx={{ width: "100%", height: 500 }} elevation={2}>
            <Grid container spacing={3}>
              <Grid item xs={6}>
                <PieHashSet data={dataMemoryRam} />
              </Grid>
              <Grid item xs={6}>
                <ChartContainer
                  width={400}
                  height={400}
                  series={[
                    {
                      type: "bar",
                      data: [getMatriz, getEstoque, getPosto],
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
                  <ChartsXAxis
                    label="X axis"
                    position="bottom"
                    axisId="x-axis-id"
                  />
                </ChartContainer>
              </Grid>
            </Grid>
          </Paper>
        )}
      </Box>
    </section>
  );
}
