package ma.website.blog.exeption;



import ma.website.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundExceptrion.class)
    public ResponseEntity<ApiResponse> exceptionHandler(ResourceNotFoundExceptrion ex){

        ApiResponse apiResponse = new ApiResponse(ex.getMessage() , false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String , String>> exceptionValadationHandler(MethodArgumentNotValidException ex){
        Map<String , String> allErreur =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err)->{
            String field = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            allErreur.put(field,message);
        });

        return new ResponseEntity<>(allErreur,HttpStatus.BAD_REQUEST);
        }
}
