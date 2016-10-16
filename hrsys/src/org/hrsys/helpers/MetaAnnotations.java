package org.hrsys.helpers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hrsys.constants.CommonConstants;
import org.springframework.security.access.prepost.PreAuthorize;

public class MetaAnnotations {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public @interface IsAdmin
    {
    }
    
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize(CommonConstants.HAS_ROLE_AUTHENTICATED)
    public @interface IsAuthenticated
    {
    }
    
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize(CommonConstants.EMPLOYEE_ID_MATCH_OR_HAS_ROLE_ADMIN)
    public @interface EmployeeIdMatchOrIsAdmin
    {
    }
    
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize(CommonConstants.EMPLOYEE_ID_MATCH)
    public @interface EmployeeIdMatch
    {
    }
}
