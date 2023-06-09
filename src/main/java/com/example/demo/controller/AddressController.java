package com.example.demo.controller;

import com.example.demo.EntityVersionReader;
import com.example.demo.model.dtos.AddressDTO;
import com.example.demo.model.dtos.AddressListResponse;
import com.example.demo.model.dtos.AddressRevisionDTO;
import com.example.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;
    private final EntityVersionReader entityVersionReader;

    @GetMapping("/all")
    public AddressListResponse getAllAddresses() {
        return new AddressListResponse(addressService.getAllAddresses());
    }

    @GetMapping("/audit")
    public Object getAudit(){
        return entityVersionReader.readLogs().stream().map(rev -> AddressRevisionDTO.builder()
                .communeCode(rev.getEntity().getCommuneCode())
                .city(rev.getEntity().getCity())
                .isDefault(rev.getEntity().isDefault())
                .houseNumber(rev.getEntity().getHouseNumber())
                .flatNumber(rev.getEntity().getFlatNumber())
                .streetName(rev.getEntity().getStreetName())
                .revNumber(rev.getUserEntityRevision().getId())
                .revisionType(rev.getRevisionType())
                .created(rev.getEntity().getCreated())
                .modified(rev.getEntity().getModified())
                .build());
    }

    @GetMapping
    public ResponseEntity<AddressDTO> getAddressById(@RequestParam(name = "id", required = false) Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressService.getAddressById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressService.addAddress(addressDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressService.updateAddress(id, addressDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
    }


}
