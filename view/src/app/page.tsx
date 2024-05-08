"use client";
import Formulary from "@/components/interface/formulary";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import * as React from "react";

function SignInSide() {
  return (
    <main>
      <React.Suspense fallback={null}>
        <ThemeProvider theme={createTheme()}>
          <Grid container component="main" sx={{ height: "100vh" }}>
            <CssBaseline />
            <Grid
              item
              xs={false}
              sm={4}
              md={7}
              sx={{
                backgroundImage:
                  "url(https://source.unsplash.com/random?wallpapers)",
                backgroundRepeat: "no-repeat",
                backgroundColor: (t) =>
                  t.palette.mode === "light"
                    ? t.palette.grey[50]
                    : t.palette.grey[900],
                backgroundSize: "cover",
                backgroundPosition: "center",
              }}
            />
            <Grid
              item
              xs={12}
              sm={8}
              md={5}
              component={Paper}
              elevation={6}
              square
            >
              <Formulary />
            </Grid>
          </Grid>
        </ThemeProvider>
      </React.Suspense>
    </main>
  );
}

export default SignInSide;
