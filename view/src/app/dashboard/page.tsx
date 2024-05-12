"use client";
import GetAllComputers from "@/api/computer/findComputer/GetAllComputers";
import PieHashSet from "@/components/interface/PieHashSet";
import { Alert, Box, Grid, Paper } from "@mui/material";
import {
  BarPlot,
  ChartContainer,
  ChartsXAxis,
  LinePlot,
  MarkPlot,
} from "@mui/x-charts";
import React, { useEffect } from "react";

export default function Dashboard() {
  const [dataMemoryRam, setDataMemoryRam] = React.useState<Set<number>>(
    new Set()
  );
  const [dataLocation, setDataLocation] = React.useState<ComputerLocation>();
  const [error, setError] = React.useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const dataComputerResponse = await GetAllComputers(
          "http://localhost:8080/v1/computer"
        );
        const dataComputerLocationResponse = await GetAllComputers(
          "http://localhost:8080/v1/computer/location"
        );

        const memoryValues = dataComputerResponse.result.map(
          (item: ComputerData) => item.hardware["memoria-ram"]
        );
        setDataMemoryRam(new Set(memoryValues));

        // Atualize o estado de dataLocation com os dados de localização
        setDataLocation(dataComputerLocationResponse.result);
      } catch (error) {
        setError(true);
      }
    };

    fetchData();
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
                <PieHashSet data={dataMemoryRam} />{" "}
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
