package com.gdemecki.crudtask.dto;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UnifiedIdentifierValidator.UnifiedIdentifierValidatorImpl.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnifiedIdentifierValidator {

	String message() default "invalid identifier. Only one value out of id/erpId/externalId is permitted!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	class UnifiedIdentifierValidatorImpl implements ConstraintValidator<UnifiedIdentifierValidator, UnifiedIdentifier> {

		@Override
		public void initialize(UnifiedIdentifierValidator constraintAnnotation) {
		}

		@Override
		public boolean isValid(UnifiedIdentifier uid, ConstraintValidatorContext context) {
			return UnifiedIdentifier.isValid(uid);
		}
	}

}
