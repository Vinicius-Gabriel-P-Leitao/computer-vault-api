package tech.vault.server.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import tech.vault.server.core.dto.ComputerRequestBuilder;
import tech.vault.server.domain.entity.values.*;
import tech.vault.server.infra.validation.Ipv4Tester;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_computer")
public class Computer {
    // NOTE: Dados gerais
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "computer_id")
    private Integer computerId;

    @Column(name = "col_user", nullable = false, length = 30)
    private String user;

    @Column(name = "col_conditions", nullable = false, length = 16) //Condição do computador
    private String computerCondition;

    @Column(name = "col_business_unit", nullable = false, length = 10) //Unidade de negocio
    private String businessUnit;

    @Column(name = "col_department", nullable = false, length = 100) //Departamento
    private String department;

    @Column(name = "col_number_patrimony", length = 6) //Numero do patrimonio
    private String numberPatrimony;

    @Column(name = "col_location_computer", nullable = false, length = 7)
    //Local do computador ('matriz','posto','estoque')
    @Enumerated(EnumType.STRING)
    private LocationComputer locationComputer;

    // NOTE: Hardware
    @Column(name = "col_computer_brand", length = 15) //Marca do computador
    private String computerBrand;

    @Column(name = "col_type_computer", nullable = false, length = 16) //Tipo do computador
    @Enumerated(EnumType.STRING)
    private TypeComputer typeComputer;

    @Column(name = "col_name_computer", nullable = false, length = 15, unique = true) //Nome do computador
    private String nameComputer;

    @NotBlank
    @Column(name = "col_ip", nullable = false, length = 15, unique = true) //Ip do computador
    private String ip;

    @Column(name = "col_cpu", length = 15) //CPU do computador
    private String cpu;

    @Column(name = "col_memory_ram") //Memoria ram do computador
    private Integer memoryRam;

    @Column(name = "col_frequency_ram") //Frequência da memória ram
    private Integer frequencyRam;

    @Column(name = "col_type_ram", nullable = false, length = 10) //Tipo da memória ram ('DDR3', 'DDR4')
    @Enumerated(EnumType.STRING)
    private TypeRam typeRam;

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
    @Column(name = "col_so", nullable = false, length = 10) //Sistema do computador
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
