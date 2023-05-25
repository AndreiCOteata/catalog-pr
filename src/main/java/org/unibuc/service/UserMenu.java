package org.unibuc.service;

import org.unibuc.exception.DataNotFoundException;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.service.AccountService;
import org.unibuc.persistance.service.AddressService;
import org.unibuc.persistance.service.EmployeeService;
import org.unibuc.persistance.service.ProfileService;
import org.unibuc.util.CustomScannerService;

public class UserMenu {

    private final AccountService accountService;
    private final AddressService addressService;
    private final ProfileService profileService;

    private final EmployeeService employeeService = new EmployeeService();

    public UserMenu(AccountService accountService, AddressService addressService,
                    ProfileService profileService){
        this.accountService = accountService;
        this.addressService = addressService;
        this.profileService = profileService;
    }
    public void menu(Long accountId, Long profileId, String firstName){
        System.out.println("Welcome, " + firstName + "!");
        System.out.println("1. Update profile");
        System.out.println("2. Withdraw money");
        System.out.println("3. Transfer money");
        System.out.println("4. Add money");

    }
}
