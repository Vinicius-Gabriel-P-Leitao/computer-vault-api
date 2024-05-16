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
import CopyrightFooter from "./Copyright";
import { useRouter } from "next/navigation";

const Formulary = () => {
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<boolean>(false);
  const router = useRouter();

  // NOTE: Evento que pega valores do forms, joga para uma função de fetch que salva no local storage e salva o valor no state
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError(null);

    const data = new FormData(event.currentTarget);
    const username = data.get("username") as string;
    const password = data.get("password") as string;

    try {
      const loginResponse = await LoginUser(username, password);

      if (loginResponse.operation) {
        // const storedToken = localStorage.getItem("token");
        console.log("Token armazenado");
        setSuccess(true);

        router.push("/dashboard");
      } else {
        setSuccess(false);
        setError("Credenciais inválidas");
      }
    } catch (error) {
      console.error("Erro ao realizar login:", error);
      setSuccess(false);
      setError("Erro ao realizar login");
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
        {/* //TODO: Separar para outro arquivo, ele é o formulário */}
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
          <FormControlLabel
            control={<Checkbox value="remember" color="primary" />}
            label="Lembre de mim"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Enviar
          </Button>

          {error && <Alert severity="error">{error}</Alert>}
          {success && (
            <Alert severity="success">Sucesso ao realizar login</Alert>
          )}

          <CopyrightFooter link="http://localhost:3000/">
            Tela de login
          </CopyrightFooter>
        </Box>
      </Box>
    </section>
  );
};

export default Formulary;
