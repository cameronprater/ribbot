package io.ribbot.core.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = NoEmojisValidator.class)
@Documented
public @interface NoEmojis {

    String message() default "{io.ribbot.core.validation.NoEmojis.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
