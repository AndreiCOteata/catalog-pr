package org.unibuc.persistance.model;

public interface Profile {
    Long getId();

    String getFirstName();

    String getLastName();

    Long getCnp();

    String getEmail();

    Long getAddressId();

    Long getPhone();
}
