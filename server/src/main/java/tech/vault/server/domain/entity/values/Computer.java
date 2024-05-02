package tech.vault.server.domain.entity.values;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.vault.server.domain.entity.User;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "computer_id")
    private UUID computerId;

    @ManyToOne
    @JoinColumn(name = "col_user_id")
    private User user;

    @Column(name = "col_conditions", nullable = false, length = 16) //Condição do computador
    private String computerCondition;

    @Column(name = "col_business_unit", nullable = false, length = 10) //Unidade de negocio
    private String businessUnit;

    @Column(name = "col_department", nullable = false, length = 100) //Departamento
    private String department;

    @Column(name = "col_number_patrimony", length = 6) //Numero do patrimonio
    private String numberPatrimony;

    @Column(name = "col_location_computer", nullable = false, length = 7) //Local do computador ('matriz','posto','estoque')
    @Enumerated(EnumType.STRING)
    private LocationComputer locationComputer;

    @Column(name = "col_computer_brand", length = 15) //Marca do computador
    private String computerBrand;

    @Column(name = "col_type_computer", nullable = false, length = 16) //Tipo do computador
    @Enumerated(EnumType.STRING)
    private TypeComputer typeComputer;

    @Column(name = "col_name_computer", nullable = false, length = 15, unique = true) //Nome do computador
    private String nameComputer;

    @Column(name = "col_ip", nullable = false, length = 15, unique = true) //Ip do computador
    private String ip;

    @Column(name = "col_cpu", length = 15) //CPU do computador
    private String cpu;

    @Column(name = "col_memory_ram") //Memoria ram do computador
    private Integer memoryRam;

    @Column(name = "col_frequency_ram") //Frequencia da memória ram
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

    @Column(name = "col_so", nullable = false, length = 10) //Sistema do computador
    @Enumerated(EnumType.STRING)
    private So nameSo;
}
