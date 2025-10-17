package EST.Baemin.Manager.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {

    private Long id;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes,
                            String nameAttributeKey,
                            Long id) {
        super(authorities, attributes, nameAttributeKey);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}