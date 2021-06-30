
package com.aitho.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Authentication {

    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    public Role role;
}
