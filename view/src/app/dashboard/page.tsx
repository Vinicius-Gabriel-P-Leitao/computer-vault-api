import DashboardLayout from "@/components/layout/DashboardLayout";
import { Box, CircularProgress } from "@mui/material";
import React from "react";

export default function Dashboard() {
  return (
    <main>
      <React.Suspense fallback={<CircularProgress color="success" />}>
        <Box sx={{ width: "100%" }}>
          <DashboardLayout />
        </Box>
      </React.Suspense>
    </main>
  );
}
