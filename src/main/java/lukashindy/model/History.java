//package lukashindy.model;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "history")
//public class History {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Currency sourceCurrency;
//    private Currency targetCurrency;
//
//    @Column(name = "source_sum", nullable = false)
//    private Double sourceSum;
//
//    @Column(name = "target_sum", nullable = false)
//    private Double targetSum;
//
//    @Column(name = "date", nullable = false)
//    private Date date;
//
//}
