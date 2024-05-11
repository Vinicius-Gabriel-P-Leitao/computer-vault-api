import LoginLayout from "@/components/layout/login-layout";
import { CircularProgress } from "@mui/material";

import * as React from "react";

function SignInSide() {
  return (
    <main>
      <React.Suspense
        fallback={
          <CircularProgress color="success" className="absolute-center" />
        }
      >
        <LoginLayout />
      </React.Suspense>
    </main>
  );
}

export default SignInSide;
