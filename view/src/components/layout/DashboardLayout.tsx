"use client"
import { GetDataComputer } from "@/api/computer/CrudComputer";
import GetDataComputerLocation from "@/api/computer/GetLocationComputer";
import { Alert, createTheme, Grid, Paper } from "@mui/material";
import { useEffect, useState } from "react";
import { ThemeProvider } from "styled-components";
import ChartContainerLocations from "../modules/ChartContainerLocations";
import PieChartHashSet from "../modules/PieChartHashSet";

export default function DashboardLayout() {
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

  const getMatriz = dataLocation?.MATRIZ;
  const getEstoque = dataLocation?.ESTOQUE;
  const getPosto = dataLocation?.POSTO;

  return (
    <ThemeProvider theme={createTheme()}>
      {error ? (
        <Alert severity="error">Erro ao buscar valores</Alert>
      ) : (
        <Grid container spacing={3}>
          <Grid item xs={6}>
            <Paper sx={{ width: "100%", height: 300 }} elevation={4}>
              <PieChartHashSet data={dataMemoryRam} />
            </Paper>
          </Grid>

          <Grid item xs={6}>
            <Paper sx={{ width: "100%", height: 500 }} elevation={4} >
              <ChartContainerLocations
                matriz={getMatriz}
                estoque={getEstoque}
                posto={getPosto}
              />
            </Paper>
          </Grid>
        </Grid>
      )}
    </ThemeProvider>
  );
}
