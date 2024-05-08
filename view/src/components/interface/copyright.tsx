import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";

type CopyrightFooterProps = {
  children?: React.ReactNode;
  link: string;
  props?: any;
};

const CopyrightFooter = ({
  children,
  link,
  ...props
}: CopyrightFooterProps) => {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}
    >
      {"Copyright © "}
      <Link color="inherit" href={link}>
        {children}
      </Link>
      {new Date().getFullYear()}
    </Typography>
  );
};

export default CopyrightFooter;
