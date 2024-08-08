package com.ebanking.api.mappers;

import com.ebanking.api.dto.AccountOperationDTO;
import com.ebanking.api.dto.CurrentBankAccountDTO;
import com.ebanking.api.dto.CustomerDTO;
import com.ebanking.api.dto.SavingBankAccountDTO;
import com.ebanking.api.models.AccountOperation;
import com.ebanking.api.models.CurrentAccount;
import com.ebanking.api.models.Customer;
import com.ebanking.api.models.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    CustomerMapperImpl customerMapper;
    public BankAccountMapperImpl(CustomerMapperImpl customerMapper) {
        this.customerMapper = customerMapper;
    }


    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();

        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        CustomerDTO customerDTO = customerMapper.fromCustomer(savingAccount.getCustomer());
        savingBankAccountDTO.setCustomer(customerDTO);
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }
    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount = new SavingAccount();

        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        Customer customer = customerMapper.fromCustomerDTO(savingBankAccountDTO.getCustomer());
        savingAccount.setCustomer(customer);

        return savingAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();

        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        CustomerDTO customerDTO = customerMapper.fromCustomer(currentAccount.getCustomer());
        currentBankAccountDTO.setCustomer(customerDTO);
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();

        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
        Customer customer = customerMapper.fromCustomerDTO(currentBankAccountDTO.getCustomer());
        currentAccount.setCustomer(customer);

        return currentAccount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();

        BeanUtils.copyProperties(accountOperation, accountOperationDTO);

        return accountOperationDTO;
    }

}
