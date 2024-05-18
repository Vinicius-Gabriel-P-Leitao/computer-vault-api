"use client"
import BackgroundSlider from "@/components/interface/BackGroundSlider";
import LoginFormulary from "@/components/modules/LoginFormulary";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import { createTheme, ThemeProvider } from "@mui/material/styles";

export default function LoginLayout() {
  return (
    <ThemeProvider theme={createTheme()}>
      <Grid container component="main" sx={{ height: "100vh" }}>
        <CssBaseline />
        <BackgroundSlider
          url="url(https://source.unsplash.com/random?wallpapers)"
          backGround="dark"
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <LoginFormulary />
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}
