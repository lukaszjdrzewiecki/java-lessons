package p76.bicycles.db.entity.wheels;

import lombok.Data;
import lombok.NoArgsConstructor;
import p76.bicycles.db.entity.BicyclePart;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class RearWheel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Rim rim;

    @ManyToOne(cascade = CascadeType.ALL)
    private RearHub rearHub;

    @ManyToOne(cascade = CascadeType.ALL)
    private Tyre tyre;

}
