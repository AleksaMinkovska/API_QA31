package datatransferobject;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder


public class GetAllContactsDto {


    List<ContactDto> contacts;  // nazvanije dolzno bitj 1:1 v sootvetstvije s JSON

}
