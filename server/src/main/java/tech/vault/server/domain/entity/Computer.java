package tech.vault.server.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import tech.vault.server.core.dto.ComputerRequestBuilder;
import tech.vault.server.domain.entity.values.*;
import tech.vault.server.infra.validation.Ipv4Tester;

@Entity
@Setter
@Getter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_computer")
public class Computer {
    // NOTE: Dados gerais
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "computer_id")
    private Integer computerId;

    @NotBlank(message = "O usuário não pode ser vazio")
    @Column(name = "col_user", nullable = false, length = 30)
    private String user;

    @NotNull(message = "A condição do computador não pode ser nula")
    @Column(name = "col_conditions", nullable = false, length = 16) //Condição do computador
    private String computerCondition;

    @NotNull(message = "A unidade de negocio não pode ser nula")
    @Column(name = "col_business_unit", nullable = false, length = 10) //Unidade de negocio
    private String businessUnit;

    @NotBlank(message = "O departamento não pode ser vazio")
    @Column(name = "col_department", nullable = false, length = 100) //Departamento
    private String department;

    @Column(name = "col_number_patrimony", length = 6) //Numero do patrimonio
    private String numberPatrimony;

    @NotNull(message = "O local do computador não pode ser nulo")
    @Column(name = "col_location_computer", nullable = false, length = 7)
    @Enumerated(EnumType.STRING)//Local do computador ('matriz','posto','estoque')
    private LocationComputer locationComputer;

    // NOTE: Hardware
    @Column(name = "col_computer_brand", length = 15) //Marca do computador
    private String computerBrand;

    @NotNull(message = "O tipo do computador não pode ser nulo")
    @Column(name = "col_type_computer", nullable = false, length = 16) //Tipo do computador
    @Enumerated(EnumType.STRING)
    private TypeComputer typeComputer;

    @NotBlank(message = "O nome do computador não pode ser vazio")
    @Column(name = "col_name_computer", nullable = false, length = 15, unique = true) //Nome do computador
    private String nameComputer;

    @Ipv4Tester(message = "Endereço IP inválido")
    @NotBlank(message = "O ip não pode ser nulo")
    @Column(name = "col_ip", nullable = false, length = 15, unique = true)
    private String ip;

    @Column(name = "col_cpu", length = 15) //CPU do computador
    private String cpu;

    @Column(name = "col_memory_ram") //Memoria ram do computador
    private Integer memoryRam;

    @Column(name = "col_frequency_ram") //Frequência da memória ram
    private Integer frequencyRam;

    @NotNull(message = "O tipo da memoria não pode ser nulo")
    @Column(name = "col_type_ram", nullable = false, length = 10) //Tipo da memória ram ('DDR3', 'DDR4')
    @Enumerated(EnumType.STRING)
    private TypeRam typeRam;

    @NotNull(message = "O modelo da memoria ram não pode ser nulo")
    @Column(name = "col_model_ram", nullable = false, length = 6) //Modelo da memória ram ('Dimm', 'Sodimm', 'Soldada')
    @Enumerated(EnumType.STRING)
    private ModelRam modelRam;

    @Column(name = "col_amount_of_ram_installed") //Quantidade de ram instalada
    private Integer amountOfRamInstalled;

    @Column(name = "col_hd") //HD do computador
    private Integer hd;

    @Column(name = "col_ssd") //SSD do computador
    private Integer ssd;

    // NOTE: Software
    @NotNull(message = "O sistema operacional não pode ser nulo")
    @Column(name = "col_so", nullable = false, length = 10) //Sistema operacional do computador
    @Enumerated(EnumType.STRING)
    private So nameSo;

    public Computer(ComputerRequestBuilder request) {
        this.user = request.generalData().user();
        this.computerCondition = request.generalData().computerCondition();
        this.businessUnit = request.generalData().businessUnit();
        this.department = request.generalData().department();
        this.numberPatrimony = request.generalData().numberPatrimony();
        this.locationComputer = request.generalData().locationComputer();
        this.computerBrand = request.hardware().computerBrand();
        this.typeComputer = request.hardware().typeComputer();
        this.nameComputer = request.hardware().nameComputer();
        this.ip = request.hardware().ip();
        this.cpu = request.hardware().cpu();
        this.memoryRam = request.hardware().memoryRam();
        this.frequencyRam = request.hardware().frequencyRam();
        this.typeRam = request.hardware().typeRam();
        this.modelRam = request.hardware().modelRam();
        this.amountOfRamInstalled = request.hardware().amountOfRamInstalled();
        this.hd = request.hardware().hd();
        this.ssd = request.hardware().ssd();
        this.nameSo = request.software().so();
    }
}