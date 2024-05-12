type DadosGerais = {
  "quem-adicionou": string;
  condições: string;
  "unidade-de-negocio": string;
  departamento: string;
  "numero-patrimonio": string;
  local: string;
};

type Hardware = {
  "marca-computador": string;
  "tipo-computador": string;
  nome: string;
  ip: string;
  processador: string;
  "memoria-ram": number;
  "frequência-ram": number;
  "tipo-ram": string;
  "modelo-ram": string;
  "quantidade-instalada": number;
  HD: number;
  SSD: number;
};

type Software = {
  "sistema-operacional": string;
};

interface ComputadorData {
  "dados-gerais": DadosGerais;
  hardware: Hardware;
  software: Software;
}
