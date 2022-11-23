/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.dto
 * Class: TripDto
 */

package ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "seats", collectionRelation = "seatss")
public class SeatsDTO extends RepresentationModel<SeatsDTO> {
    private Integer id;
    private String section;
    private String  number;
    private Integer price;
    private String  reservationStatus;
    private String event; // added
}
