"use client";
import { GetDataComputer } from "@/api/computer/RequestDataComputer";
import { Alert, Box, createTheme, Grid, Paper } from "@mui/material";
import { BarChart } from "@mui/x-charts";
import { useEffect, useState } from "react";
import { ThemeProvider } from "styled-components";
import ComputerQuantity from "../interface/ComputerQuantity";
import LineChartLocationComputer from "../modules/LineChartLocationComputer";
import PieChartMemoryRam from "../modules/PieChartMemoryRam";
import BarChartStorageComputer from "../modules/BarChartStorageComputer";

interface DashboardState {
  computerQuantity?: ComputerQuantity;
  dataMemoryRam?: ComputerMemoryRam | undefined;
  dataLocation?: ComputerLocation | undefined;
  dataStorage?:
    | { hd: Record<string, number>; ssd: Record<string, number> }
    | undefined;
  error: boolean;
}

const initialState: DashboardState = {
  computerQuantity: { total: 0 },
  error: false,
};

export default function DashboardLayout() {
  const [state, setState] = useState<DashboardState>(initialState);

  const dataDashboard = async () => {
    try {
      const dataMemoryRamComputer = await GetDataComputer(
        "http://localhost:8080/v1/computer/memory-ram"
      );

      const dataAllComputersStock = await GetDataComputer(
        "http://localhost:8080/v1/computer/all-stock"
      );

      const dataLocationsComputer = await GetDataComputer(
        "http://localhost:8080/v1/computer/location"
      );

      const dataStorageComputer = await GetDataComputer(
        "http://localhost:8080/v1/computer/computer-storage"
      );

      setState({
        ...state,
        computerQuantity: dataAllComputersStock?.result?.total
          ? { total: dataAllComputersStock.result.total }
          : { total: 0 },
        dataMemoryRam: dataMemoryRamComputer?.result
          ? dataMemoryRamComputer.result
          : undefined,
        dataLocation: dataLocationsComputer?.result
          ? dataLocationsComputer.result
          : undefined,
        dataStorage: {
          hd: dataStorageComputer?.result?.hd ?? {},
          ssd: dataStorageComputer?.result?.ssd ?? {},
        },
        error: false,
      });
    } catch (error: any) {
      setState({
        ...state,
        error: true,
      });
    }
  };

  useEffect(() => {
    dataDashboard();

    const intervalId = setInterval(() => {
      dataDashboard();
    }, 10000);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <ThemeProvider theme={createTheme()}>
      <Box sx={{ padding: 2 }}>
        {state.error ? (
          <Grid
            container
            direction="row"
            justifyContent="center"
            alignItems="center"
          >
            <Alert variant="filled" severity="error">
              Erro ao buscar valores
            </Alert>
          </Grid>
        ) : (
          <Box>
            <Grid container spacing={3}>
              <Grid item lg={3} md={3} sm={6} xs={12}>
                <ComputerQuantity
                  sx={{ height: "40%" }}
                  value={state.computerQuantity || { total: 0 }}
                />
              </Grid>

              <Grid item lg={3} md={3} sm={6} xs={12}>
                <LineChartLocationComputer
                  matriz={state.dataLocation?.MATRIZ}
                  estoque={state.dataLocation?.ESTOQUE}
                  posto={state.dataLocation?.POSTO}
                />
              </Grid>

              <Grid item lg={3} md={3} sm={6} xs={12}>
                <BarChartStorageComputer
                  dataStorage={
                    state.dataStorage || {
                      hd: {},
                      ssd: {},
                    }
                  }
                />
              </Grid>
            </Grid>
            <Grid item lg={3} md={3} sm={6} xs={12}>
              <PieChartMemoryRam data={state.dataMemoryRam} />
            </Grid>
          </Box>
        )}
      </Box>
    </ThemeProvider>
  );
}
