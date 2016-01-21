/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;


import org.aopalliance.intercept.MethodInvocation;
import org.seedstack.seed.SeedException;
import org.seedstack.seed.core.utils.SeedReflectionUtils;

import javax.inject.Inject;
import javax.validation.*;
import javax.validation.executable.ExecutableValidator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import static org.seedstack.seed.core.utils.SeedReflectionUtils.cleanProxy;

/**
 * Handles static validation, and "by contract" validation thanks to Validator and ExecutableValidator.
 */
class ValidationServiceInternal implements ValidationService {

    @Inject
    private ValidatorFactory validatorFactory;

    @Override
    public <T> void staticallyHandle(T candidate) {
        Set<ConstraintViolation<T>> constraintViolations = validatorFactory.getValidator().validate(candidate);
        throwsExceptionIfViolation(constraintViolations);
    }

    private <T> void throwsExceptionIfViolation(Set<ConstraintViolation<T>> constraintViolations) {
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(buildErrorMessage(constraintViolations), constraintViolations);
        }
    }

    private <T> String buildErrorMessage(Set<ConstraintViolation<T>> constraintViolations) {
        StringBuilder errorMessage = new StringBuilder();
        boolean first = true;
        for (ConstraintViolation<T> violation : constraintViolations) {
            if (first) {
                addHeader(errorMessage, violation);
                first = false;
            }
            addViolationMessage(errorMessage, violation);
        }
        return errorMessage.toString();
    }

    private <T> void addHeader(StringBuilder errorMessage, ConstraintViolation<T> violation) {
        Class<?> rootBeanClass = cleanProxy(violation.getRootBeanClass());
        errorMessage.append(rootBeanClass.getName());
    }

    private <T> void addViolationMessage(StringBuilder errorMessage, ConstraintViolation<T> violation) {
        errorMessage.append("\n")
                .append("\t").append(violation.getPropertyPath()).append(" - ").append(violation.getMessage());
    }


    @Override
    public Object dynamicallyHandleAndProceed(MethodInvocation invocation) throws Throwable {
        ExecutableValidator executableValidator = validatorFactory.getValidator().forExecutables();
        if (executableValidator == null) {
            throw SeedException.createNew(ValidationErrorCode.DYNAMIC_VALIDATION_IS_NOT_SUPPORTED);
        }
        // TODO : add support for constraint groups

        validateParameters(invocation, executableValidator);
        Object returnValue = invocation.proceed();
        validateReturnValue(invocation, executableValidator, returnValue);
        return returnValue;
    }

    private void validateParameters(MethodInvocation invocation, ExecutableValidator executableValidator) {
        Set<ConstraintViolation<Object>> parametersConstraintViolations = executableValidator
                .validateParameters(invocation.getThis(), invocation.getMethod(), invocation.getArguments() /*, groups */);
        throwsExceptionIfViolation(parametersConstraintViolations);
    }

    private void validateReturnValue(MethodInvocation invocation, ExecutableValidator executableValidator, Object returnValue) {
        Set<ConstraintViolation<Object>> returnValueConstraintViolations = executableValidator
                .validateReturnValue(invocation.getThis(), invocation.getMethod(), returnValue /*, groups*/);
        throwsExceptionIfViolation(returnValueConstraintViolations);
    }

    @Override
    public boolean candidateForStaticValidation(Class<?> candidate) {
        for (Field field : candidate.getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (hasConstraintOrValidAnnotation(annotation)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasConstraintOrValidAnnotation(Annotation annotation) {
        return SeedReflectionUtils.hasAnnotationDeep(annotation.annotationType(), Constraint.class) || Valid.class.equals(annotation.annotationType());
    }

    @Override
    public boolean candidateForDynamicValidation(Class<?> candidate) {
        for (Method method : candidate.getDeclaredMethods()) {
            if (candidateForDynamicValidation(method)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean candidateForDynamicValidation(Method candidate) {
        return shouldValidateParameters(candidate) || shouldValidateReturnType(candidate);
    }

    private boolean shouldValidateParameters(Method candidate) {
        for (Annotation[] annotationsForOneParameter : candidate.getParameterAnnotations()) {
            for (Annotation annotation : annotationsForOneParameter) {
                if (hasConstraintOrValidAnnotation(annotation)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shouldValidateReturnType(Method candidate) {
        for (Annotation annotation : candidate.getAnnotations()) {
            if (hasConstraintOrValidAnnotation(annotation)) {
                return true;
            }
        }
        return false;
    }
}
