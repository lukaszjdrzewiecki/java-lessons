package p76.bicycles.db.entity.drivetrain;

import lombok.Data;
import lombok.NoArgsConstructor;
import p76.bicycles.db.entity.BicyclePart;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Crank extends BicyclePart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String defaultChainRings;
    private int pcdBig;
    private int pcdSmall;
    private String armsAndType;
    private int armLength;
    private int numberOfGears;
    private int speeds;
    private int qfactor;
    private double chainline;

    @ManyToOne(cascade = CascadeType.ALL)
    private Chainring bigGear;

    @ManyToOne(cascade = CascadeType.ALL)
    private Chainring mediumGear;

    @ManyToOne(cascade = CascadeType.ALL)
    private Chainring smallGear;


    public Crank(String name) {
        super(name);
    }

    // 3 GEARS
    public Crank(String name, String brand, double weight, int pcdBig, int pcdSmall, String armsAndType, int armLength, int numberOfGears, int speeds, Chainring bigGear, Chainring mediumGear, Chainring smallGear) {
        super(name, brand, weight);
        this.pcdBig = pcdBig;
        this.pcdSmall = pcdSmall;
        this.armsAndType = armsAndType;
        this.armLength = armLength;
        this.numberOfGears = numberOfGears;
        this.bigGear = bigGear;
        this.mediumGear = mediumGear;
        this.smallGear = smallGear;
        this.speeds = speeds;
    }

    // 2 GEARS
    public Crank(String name, String brand, double weight, int pcdBig, int pcdSmall, String armsAndType, int armLength, int numberOfGears, int speeds, Chainring bigGear, Chainring smallGear) {
        super(name, brand, weight);
        this.pcdBig = pcdBig;
        this.pcdSmall = pcdSmall;
        this.armsAndType = armsAndType;
        this.armLength = armLength;
        this.numberOfGears = numberOfGears;
        this.bigGear = bigGear;
        this.smallGear = smallGear;
        this.speeds = speeds;
    }

    // 1 GEAR
    public Crank(String name, String brand, double weight, int pcdBig, String armsAndType, int armLength, int numberOfGears, int speeds, Chainring bigGear) {
        super(name, brand, weight);
        this.pcdBig = pcdBig;
        this.armsAndType = armsAndType;
        this.armLength = armLength;
        this.numberOfGears = numberOfGears;
        this.bigGear = bigGear;
        this.speeds = speeds;
    }

    // 3 GEARS Complete
    public Crank(String name, String brand, String productClass, String purpose, double weight, String defaultChainRings, int pcdBig, int pcdSmall, String armsAndType, int armLength, int numberOfGears, int speeds, int qfactor, double chainline, Chainring bigGear, Chainring mediumGear, Chainring smallGear) {
        super(name, brand, productClass, purpose, weight);
        this.defaultChainRings = defaultChainRings;
        this.pcdBig = pcdBig;
        this.pcdSmall = pcdSmall;
        this.armsAndType = armsAndType;
        this.armLength = armLength;
        this.numberOfGears = numberOfGears;
        this.speeds = speeds;
        this.qfactor = qfactor;
        this.chainline = chainline;
        this.bigGear = bigGear;
        this.mediumGear = mediumGear;
        this.smallGear = smallGear;
    }

    // 2 GEARS Complete
    public Crank(String name, String brand, String productClass, String purpose, double weight, String defaultChainRings, int pcdBig, int pcdSmall, String armsAndType, int armLength, int numberOfGears, int speeds, int qfactor, double chainline, Chainring bigGear, Chainring smallGear) {
        super(name, brand, productClass, purpose, weight);
        this.defaultChainRings = defaultChainRings;
        this.pcdBig = pcdBig;
        this.pcdSmall = pcdSmall;
        this.armsAndType = armsAndType;
        this.armLength = armLength;
        this.numberOfGears = numberOfGears;
        this.speeds = speeds;
        this.qfactor = qfactor;
        this.chainline = chainline;
        this.bigGear = bigGear;
        this.smallGear = smallGear;
    }

    // 1 GEAR Complete
    public Crank(String name, String brand, String productClass, String purpose, double weight, String defaultChainRings, int pcdBig, String armsAndType, int armLength, int numberOfGears, int speeds, int qfactor, double chainline, Chainring bigGear) {
        super(name, brand, productClass, purpose, weight);
        this.defaultChainRings = defaultChainRings;
        this.pcdBig = pcdBig;
        this.armsAndType = armsAndType;
        this.armLength = armLength;
        this.numberOfGears = numberOfGears;
        this.speeds = speeds;
        this.qfactor = qfactor;
        this.chainline = chainline;
        this.bigGear = bigGear;
    }
}