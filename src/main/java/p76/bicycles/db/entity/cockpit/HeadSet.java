package p76.bicycles.db.entity.cockpit;

import lombok.Data;
import lombok.NoArgsConstructor;
import p76.bicycles.db.entity.BicyclePart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class HeadSet extends BicyclePart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private double stereerDiameter;
    private double topFrameDiameter;
    private double bottomFrameDiameter;
    private double topHeadTubeDiameter;
    private double bottomHeadTubeDiameter;
    private Boolean tapered;
    private String type;

}