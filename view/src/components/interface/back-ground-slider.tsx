import { Grid } from "@mui/material";

type BackgroundSliderProps = {
  url: string;
  backGround: string;
};

const BackgroundSlider = (props: BackgroundSliderProps) => {
  return (
    <Grid
      item
      xs={false}
      sm={4}
      md={7}
      sx={{
        backgroundImage: `${props.url}`,
        backgroundRepeat: "no-repeat",
        backgroundColor: (t) =>
          t.palette.mode === `${props.backGround}`
            ? t.palette.grey[50]
            : t.palette.grey[900],
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    />
  );
};

export default BackgroundSlider;
