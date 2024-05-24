"use client";
import { GetDataComputer } from "@/api/computer/CrudComputer";
import GetDataComputerLocation from "@/api/computer/GetLocationComputer";
import { Alert, createTheme, Grid, Modal, Paper } from "@mui/material";
import { useEffect, useState } from "react";
import { ThemeProvider } from "styled-components";
import ChartContainerLocations from "../modules/ChartContainerLocations";
import PieChartHashSet from "../modules/PieChartHashSet";
import ComputerQuantity from "../interface/ComputerQuantity";

//NOTE: Interface para o dashboard data
interface DashboardState {
  computerQuantity: number;
  dataMemoryRam: Set<number>;
  dataLocation?: ComputerLocation;
  error: boolean;
}

//NOTE: Define um estado inicial
const initialState: DashboardState = {
  computerQuantity: 0,
  dataMemoryRam: new Set(),
  dataLocation: undefined,
  error: false,
};

export default function DashboardLayout() {
  const [state, setState] = useState<DashboardState>(initialState);

  const dataDashboard = async () => {
    try {
      const dataComputer = await GetDataComputer(
        "http://localhost:8080/v1/computer"
      );
      const dataLocation = await GetDataComputerLocation();

      const memoryData = dataComputer.result.map(
        (item: ComputerData) => item.hardware["memoria-ram"]
      );

      const computerQuantity = memoryData.length;

      setState({
        ...state,
        computerQuantity,
        dataMemoryRam: new Set(memoryData),
        dataLocation: dataLocation.result,
        error: false,
      });
    } catch (error: any) {
      //NOTE: Preserva os estados antigos e muda só o de erro
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

  const getMatriz = state.dataLocation?.MATRIZ;
  const getEstoque = state.dataLocation?.ESTOQUE;
  const getPosto = state.dataLocation?.POSTO;

  return (
    <ThemeProvider theme={createTheme()}>
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
        <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
          <Grid item lg={3} xs={6}>
            <ComputerQuantity
              sx={{ height: "100%" }}
              value={state.computerQuantity.toString()}
            />
          </Grid>
          <Grid
            container
            direction="row"
            justifyContent="center"
            alignItems="center"
          >
            <Grid item xs={6}>
              <PieChartHashSet data={state.dataMemoryRam} />
            </Grid>
            <Grid item>
              <ChartContainerLocations
                matriz={getMatriz}
                estoque={getEstoque}
                posto={getPosto}
              />
            </Grid>
          </Grid>
        </Grid>
      )}
    </ThemeProvider>
  );
}
