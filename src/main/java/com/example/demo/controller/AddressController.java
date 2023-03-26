package com.example.demo.controller;

import com.example.demo.model.dtos.AddressDTO;
import com.example.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/all")
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping
    public AddressDTO getAddressById(@RequestParam(name = "id", required = false) Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public AddressDTO addAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.addAddress(addressDTO);
    }

    @PutMapping("/{id}")
    public AddressDTO updateAddress(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) {
        return addressService.updateAddress(id, addressDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
    }


}
