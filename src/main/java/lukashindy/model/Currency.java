package lukashindy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "numCode")
    private String numCode;

    @Column(name = "charCode")
    private String charCode;

    @Column(name = "nominal")
    private Integer nominal;

    @Column(name = "name")
    private String name;

    public Currency(String id, String numCode, String charCode, Integer nominal, String name) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
    }
}
