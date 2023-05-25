package org.unibuc.service;

import org.unibuc.exception.DataNotFoundException;
import org.unibuc.persistance.dto.AccountDto;
import org.unibuc.persistance.dto.AddressDto;
import org.unibuc.persistance.dto.ProfileDto;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.model.Employee;
import org.unibuc.persistance.model.Profile;
import org.unibuc.persistance.service.AccountService;
import org.unibuc.persistance.service.AddressService;
import org.unibuc.persistance.service.EmployeeService;
import org.unibuc.persistance.service.ProfileService;
import org.unibuc.reader.AccountReader;
import org.unibuc.util.CustomScannerService;
import org.unibuc.util.PasswordEncryptionService;

public class BankApp {
    private static final PasswordEncryptionService passwordEncryptionService = PasswordEncryptionService.getInstance();
    private static final AccountService accountService = new AccountService();
    private static final AddressService addressService = new AddressService();
    private static final ProfileService profileService = new ProfileService();

    private static final EmployeeService employeeService = new EmployeeService();

    public static void start(String input){
        while(!input.equals("exit")){
            System.out.println("Welcome to BankApp");
            System.out.println("    1. Login");
            System.out.println("    2. Create/Register account");
            System.out.println("    2. Exit");
            if(input.isBlank() || input.isEmpty()){
                input = CustomScannerService.getStringValue();
            }
            switch (input) {
                case "login" -> {
                    try {
                        System.out.println("Please insert your credentials");
                        AccountDto accountDto = AccountReader.readAccount();
                        Account account = accountService.findByUsername(accountDto.getUsername());
                        if (passwordEncryptionService.checkPass(accountDto.getPassword(), account.getPassword())) {
                            menu(account.getId(), account.getProfileId());
                        } else {
                            System.out.println("Invalid Credentials!");
                        }
                    }catch (DataNotFoundException exception){
                        exception.printStackTrace();
                    }
                }
                case "register" -> {
                    try {
                        System.out.println("Please insert the following details");
                        System.out.println("1. Account details");
                        AccountDto dto = AccountReader.readAccount();
                        ProfileDto profileDto = AccountReader.readProfile();
                        AddressDto addressDto = AccountReader.readAddress();
                        profileDto.setAddressId(addressService.save(addressDto).getId());
                        dto.setProfielId(profileService.save(profileDto).getId());
                        dto.setPassword(passwordEncryptionService.hashPassword(dto.getPassword()));
                        accountService.save(dto);
                    } catch (DataNotFoundException exception){
                        exception.printStackTrace();
                    }
                }
                default -> System.out.println("Not a valid option, please choose one of the following: ");
            }
            input = "";
        }
    }

    public static void menu(Long accountId, Long profileId){
        try {
            Profile profile = profileService.find(profileId);
            if (employeeService.isEmployee(profileId)) {
                Employee employee = employeeService.find(profileId);
                ManagementMenu managementMenu = new ManagementMenu(accountService, addressService,profileService);
                managementMenu.menu(profileId, accountId, profile.getFirstName(), employee.getBranchId(), employee.getId());

            } else {
                UserMenu userMenu = new UserMenu(accountService, addressService,profileService);
                userMenu.menu(accountId, profileId, profile.getFirstName());
            }
        } catch (DataNotFoundException exception){
            exception.printStackTrace();
        }
    }
}
