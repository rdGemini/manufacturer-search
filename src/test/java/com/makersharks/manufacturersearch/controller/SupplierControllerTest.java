package com.makersharks.manufacturersearch.controller;

import com.makersharks.manufacturersearch.entity.Supplier;
import com.makersharks.manufacturersearch.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupplierController.class)
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    private List<Supplier> supplierDTOs;

    @BeforeEach
    public void setUp() {
        Supplier supplier1 = new Supplier(
                1L,
                "ABC Manufacturing",
                "http://abcmanufacturing.com",
                "India",
                Supplier.NatureOfBusiness.SMALL_SCALE,
                new HashSet<>(Arrays.asList(Supplier.ManufacturingProcess.PRINTING_3D, Supplier.ManufacturingProcess.MOULDING))
        );

        Supplier supplier2 = new Supplier(
                2L,
                "XYZ Industries",
                "http://xyzindustries.com",
                "India",
                Supplier.NatureOfBusiness.SMALL_SCALE,
                new HashSet<>(Collections.singletonList(Supplier.ManufacturingProcess.CASTING))
        );

        supplierDTOs = Arrays.asList(supplier1, supplier2);
    }

    @Test
    public void testQuerySuppliers() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(supplierService.findSuppliers("India", Supplier.NatureOfBusiness.SMALL_SCALE, Supplier.ManufacturingProcess.PRINTING_3D, pageable))
                .thenReturn(new PageImpl<>(supplierDTOs, pageable, supplierDTOs.size()));

        mockMvc.perform(post("/api/supplier/query")
                        .param("location", "India")
                        .param("natureOfBusiness", "SMALL_SCALE")
                        .param("manufacturingProcess", "PRINTING_3D")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].supplierId").value(1))
                .andExpect(jsonPath("$[0].companyName").value("ABC Manufacturing"))
                .andExpect(jsonPath("$[0].website").value("http://abcmanufacturing.com"))
                .andExpect(jsonPath("$[0].location").value("India"))
                .andExpect(jsonPath("$[0].natureOfBusiness").value("SMALL_SCALE"))
                .andExpect(jsonPath("$[0].manufacturingProcesses", hasSize(2)))
                .andExpect(jsonPath("$[1].supplierId").value(2))
                .andExpect(jsonPath("$[1].companyName").value("XYZ Industries"));
    }

    @Test
    public void testQuerySuppliers_NoResults() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(supplierService.findSuppliers("NonExistentLocation", Supplier.NatureOfBusiness.SMALL_SCALE, Supplier.ManufacturingProcess.PRINTING_3D, pageable))
                .thenReturn(new PageImpl<>(Collections.emptyList(), pageable, 0));

        mockMvc.perform(post("/api/supplier/query")
                        .param("location", "NonExistentLocation")
                        .param("natureOfBusiness", "SMALL_SCALE")
                        .param("manufacturingProcess", "PRINTING_3D")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
