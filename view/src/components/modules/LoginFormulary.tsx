import LoginUser from "@/api/auth/Login";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import { Alert } from "@mui/material";
import Avatar from "@mui/material/Avatar";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import { useState } from "react";
import CopyrightFooter from "../interface/Copyright";
import { useRouter } from "next/navigation";

const LoginFormulary = () => {
  const [messageFetch, setMessageFetch] = useState<string | null>(null);
  const [statusFetch, setStatusFetch] = useState<boolean>(false);
  const router = useRouter();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setMessageFetch(null);

    const data = new FormData(event.currentTarget);
    const username = data.get("username") as string;
    const password = data.get("password") as string;

    try {
      const loginResponse = await LoginUser(username, password);

      if (loginResponse.operation) {
        setMessageFetch(loginResponse.message);
        setStatusFetch(true);

        router.push("/dashboard");
      } else {
        setMessageFetch(loginResponse.message);
        setStatusFetch(false);
      }
    } catch (error) {
      setMessageFetch("Erro ao realizar login: " + error);
      setStatusFetch(false);
    }
  };

  return (
    <section>
      <Box
        sx={{
          my: 8,
          mx: 4,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Login
        </Typography>
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="username"
            label="Nome de usuário"
            name="username"
            autoComplete="username"
            autoFocus
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Enviar
          </Button>
          
          {statusFetch && (
            <Alert severity={statusFetch ? "success" : "error"}>
              {messageFetch}
            </Alert>
          )}

          <CopyrightFooter link="http://localhost:3000/">
            Tela de login
          </CopyrightFooter>
        </Box>
      </Box>
    </section>
  );
};

export default LoginFormulary;
