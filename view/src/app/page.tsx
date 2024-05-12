import LoginLayout from "@/components/layout/LoginLayout";
import { CircularProgress } from "@mui/material";
import * as React from "react";

function SignInSide() {
  return (
    <main>
      <React.Suspense fallback={<CircularProgress color="success" />}>
        <LoginLayout />
      </React.Suspense>
    </main>
  );
}

export default SignInSide;
