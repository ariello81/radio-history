package pl.ryzykowski.radiohistory.security;

import org.springframework.security.core.GrantedAuthority;
import pl.ryzykowski.radiohistory.entity.Authority;

public class AuthorityAdapter implements GrantedAuthority {

    private final Authority authority;

    public AuthorityAdapter(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
