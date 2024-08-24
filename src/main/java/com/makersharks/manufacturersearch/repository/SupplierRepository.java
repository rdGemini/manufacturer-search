package com.makersharks.manufacturersearch.repository;

import com.makersharks.manufacturersearch.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(
            String location,
            Supplier.NatureOfBusiness natureOfBusiness,
            Supplier.ManufacturingProcess manufacturingProcess,
            Pageable pageable);
}
