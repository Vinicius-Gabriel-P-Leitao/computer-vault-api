import { Computer } from "@mui/icons-material";
import Avatar from "@mui/material/Avatar";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Stack from "@mui/material/Stack";
import type { SxProps } from "@mui/material/styles";
import Typography from "@mui/material/Typography";
import * as React from "react";

export interface BudgetProps {
  sx?: SxProps;
  value: string;
}

const ComputerQuantity = ({ sx, value }: BudgetProps): React.JSX.Element => {
  return (
    <Card sx={sx}>
      <CardContent>
        <Stack spacing={3}>
          <Stack
            direction="row"
            sx={{ alignItems: "flex-start", justifyContent: "space-between" }}
            spacing={3}
          >
            <Stack spacing={1}>
              <Typography color="text.secondary" variant="overline">
                Quantidade em estoque
              </Typography>
              <Typography variant="h4">{value}</Typography>
            </Stack>
            <Avatar
              sx={{
                backgroundColor: "var(--mui-palette-primary-main)",
                height: "56px",
                width: "56px",
              }}
            >
              <Computer fontSize="large"/>
            </Avatar>
          </Stack>
        </Stack>
      </CardContent>
    </Card>
  );
};

export default ComputerQuantity;
