package com.makersharks.manufacturersearch.service;

import com.makersharks.manufacturersearch.entity.Supplier;
import com.makersharks.manufacturersearch.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> findSuppliers(String location, Supplier.NatureOfBusiness natureOfBusiness, Supplier.ManufacturingProcess manufacturingProcess, Pageable pageable) {
        return supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(
                location, natureOfBusiness, manufacturingProcess, pageable);
    }
}
