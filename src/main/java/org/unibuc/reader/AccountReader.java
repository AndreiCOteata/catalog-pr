package org.unibuc.reader;

import org.unibuc.persistance.dto.AccountDto;
import org.unibuc.persistance.dto.AddressDto;
import org.unibuc.persistance.dto.ProfileDto;
import org.unibuc.util.CustomScannerService;

public class AccountReader {
    public static AccountDto readAccount(){
        AccountDto dto = new AccountDto();
        System.out.print("Username: ");
        dto.setUsername(CustomScannerService.getStringValue());
        System.out.print("Password: ");
        dto.setPassword(CustomScannerService.getStringValue());
        return dto;
    }

    public static ProfileDto readProfile(){
        ProfileDto dto = new ProfileDto();
        System.out.println("2. Profile details");
        System.out.print("First Name: ");
        dto.setFirstName(CustomScannerService.getStringValue());
        System.out.print("Last Name: ");
        dto.setLastName(CustomScannerService.getStringValue());
        System.out.print("Email: ");
        dto.setEmail(CustomScannerService.getStringValue());
        System.out.print("CNP: ");
        dto.setCnp(CustomScannerService.getLongValue());
        System.out.print("Phone: ");
        dto.setPhone(CustomScannerService.getLongValue());
        return dto;
    }

    public static AddressDto readAddress(){
        AddressDto dto = new AddressDto();
        System.out.println("3. Address details");
        System.out.print("Street Name: ");
        dto.setStreet(CustomScannerService.getStringValue());
        System.out.print("City: ");
        dto.setCity(CustomScannerService.getStringValue());
        System.out.print("Country: ");
        dto.setCountry(CustomScannerService.getStringValue());
        System.out.print("Street number: ");
        dto.setNumber(CustomScannerService.getLongValue());
        return dto;
    }

}
