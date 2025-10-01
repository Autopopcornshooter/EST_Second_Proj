package EST.Baemin.Manager.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    public static String getCurrentUserLoginId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null||!authentication.isAuthenticated()){
            return null;
        }
        return authentication.getName();    //loginId 반환
    }

    public static UserDetails getCurrentUserDetails(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&& authentication.getPrincipal() instanceof UserDetails userDetails){
            return userDetails;
        }
        return null;
    }
}
