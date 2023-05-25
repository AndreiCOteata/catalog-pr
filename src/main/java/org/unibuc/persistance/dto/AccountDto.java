package org.unibuc.persistance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto{
    private String username;

    private String password;

    private Long profielId;

    public Long getProfielId() {
        return profielId;
    }

    public void setProfielId(Long profielId) {
        this.profielId = profielId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
