package datatransferobject;


import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder


public class ErrorDto {


//    {
//             "code": 0,
//            "details": "string",
//            "message": "string",
//            "timestamp": "2022-03-07T17:10:17.905Z"
//    }


    int code;
    String details;
    String message;


}

