package org.unibuc.service;

import org.unibuc.exception.DataNotFoundException;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.service.*;
import org.unibuc.util.CustomScannerService;

public class ManagementMenu {

    private final AccountService accountService;
    private final AddressService addressService;
    private final ProfileService profileService;

    private final EmployeeService employeeService = new EmployeeService();

    private final BankAccountService bankAccountService = new BankAccountService();

    public ManagementMenu(AccountService accountService, AddressService addressService,
                    ProfileService profileService){
        this.accountService = accountService;
        this.addressService = addressService;
        this.profileService = profileService;
    }

    public void menu(Long accountId, Long profileId, String firstName, Long branchId, Long employeeId){
        System.out.println("Welcome, " + firstName + "!");

    }

    private void options(Long branchId, Long employeeId){
        System.out.println("1. Create Bank account");
        System.out.println("2. Get client details");
        System.out.println("2. Add service to client");
        System.out.println("3. List bank accounts created");
        System.out.println("5. List today's transactions");
        System.out.println("6. Issue Card");
        String input = CustomScannerService.getStringValue();
        try {
            while(!input.equals("logout")){
                switch (input) {
                    case "create" -> {
                        createBankAccount(branchId, employeeId);
                        input = "";
                    }
                    case "get" -> {

                    }
                    default -> {}
                }
            }
        } catch (DataNotFoundException exception){
            exception.printStackTrace();
        }



    }

    private static void isManager(){
        System.out.println("7. Add employee");
        System.out.println("8. List employees on branch");
        System.out.println("9. Update employee details");
        System.out.println("10. Update employee details");
    }

    private void createBankAccount(Long branchId, Long employeeId) throws DataNotFoundException {
        System.out.println("Insert username: ");
        String username = CustomScannerService.getStringValue();
        Account account = this.accountService.findByUsername(username);
        bankAccountService.save(account.getId(), employeeId, branchId);
    }
}
