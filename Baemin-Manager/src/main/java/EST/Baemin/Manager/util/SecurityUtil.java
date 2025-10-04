package EST.Baemin.Manager.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    //로그인 되어있는 유저 로그인 id 반환
    public static String getCurrentUserLoginId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null||!authentication.isAuthenticated()){
            return null;
        }
        return authentication.getName();    //loginId 반환
    }
    //로그인 되어있는지 판별
    public static boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }
    //로그인된 유저의 전체 detail 반환
    public static UserDetails getCurrentUserDetails(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&& authentication.getPrincipal() instanceof UserDetails userDetails){
            return userDetails;
        }
        return null;
    }
}
