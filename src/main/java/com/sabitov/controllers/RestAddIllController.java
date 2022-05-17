package com.sabitov.controllers;

import com.sabitov.dto.IllDto;
import com.sabitov.models.Account;
import com.sabitov.models.Ill;
import com.sabitov.services.AccountService;
import com.sabitov.services.IllService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class RestAddIllController {

    private final IllService illService;
    private final AccountService accountService;

    @PostMapping("/add_ill/upload")
    public ResponseEntity<?> get(IllDto illDto, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("patient");
        String name = illDto.getName();
        if (account == null) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
        if (name == null) {
            return new ResponseEntity<>("Empty name", HttpStatus.BAD_REQUEST);
        }
        Ill ill = illService.findIllByName(name);
        if (account.getIllness() != null) {
            if (account.getIllness().contains(ill)) {
                return new ResponseEntity<>("Patient already has this ill", HttpStatus.BAD_REQUEST);
            }
        }
        illService.addToAccount(account, ill);
        return new ResponseEntity<>("Added ill: " + name, HttpStatus.OK);
    }

    @PostMapping("/add_ill/delete")
    public ResponseEntity<?> delete(@RequestParam("deleted_ill") String illName, HttpServletRequest request){
        System.err.println(illName);
        Ill illByName = illService.findIllByName(illName);
        Account account = (Account) request.getSession().getAttribute("patient");
        System.err.println(account);
        accountService.deleteIll(account,illByName);
        System.err.println("after :" + account);
        System.err.println("after :" + illByName);
        return new ResponseEntity<>("Deleted Ill: " + illName, HttpStatus.OK);
    }
}
