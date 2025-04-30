package com.manpower.util;

import com.manpower.common.Contants;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityUtil {

    public static Integer getCompanyClaim() {
        return Integer.parseInt((String)getClaim(Contants.RateType.Claims.COMPANY_ID.name()));
    }


    public static Integer getUserClaim() {
        return Integer.parseInt((String)getClaim(Contants.RateType.Claims.USER_ID.name()));
    }

        public static Object getClaim(String claimKey) {
        // Retrieve the authentication object from SecurityContext
        UsernamePasswordAuthenticationToken authentication =
          (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Get the claims stored in the principal
            Map<String, Object> claims = (Map<String, Object>) authentication.getPrincipal();
            return claims.get(claimKey);

        }
        return null;
    }
}
