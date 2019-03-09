package p76.bicycles.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Brakes extends BicyclePart {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

}
