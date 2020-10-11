package org.okten.javaadvanced.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqMovieTittleValidator.class)
public @interface UniqMovieTittle {
    String message() default "Movie tittle should be uniq";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
