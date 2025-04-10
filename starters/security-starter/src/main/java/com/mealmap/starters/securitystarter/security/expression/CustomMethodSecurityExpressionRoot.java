package com.mealmap.starters.securitystarter.security.expression;

import com.mealmap.starters.securitystarter.security.util.SecurityUtils;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class CustomMethodSecurityExpressionRoot
        extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean isActive() {
        return SecurityUtils.isActive(this.getAuthentication());
    }

    public boolean isBlocked() {
        return SecurityUtils.isBlocked(this.getAuthentication());
    }

    public boolean isTemporaryBlocked() {
        return SecurityUtils.isTemporaryBlocked(this.getAuthentication());
    }

    public boolean isOrganizationMember(Long organizationId) {
        return SecurityUtils.isOrganizationMember(this.getAuthentication(), organizationId);
    }

    public boolean hasUserId(UUID userId) {
        return SecurityUtils.hasUserId(this.getAuthentication(), userId);
    }

    public boolean hasUserId(String userId) {
        return SecurityUtils.hasUserId(this.getAuthentication(), UUID.fromString(userId));
    }

    public boolean isApplicationService() {
        return SecurityUtils.isApplicationService(this.getAuthentication());
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
