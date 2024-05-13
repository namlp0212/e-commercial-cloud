package com.namlp0212.accounts.controller;

import com.namlp0212.accounts.dto.CustomerDetailDto;
import com.namlp0212.accounts.dto.ErrorResponseDto;
import com.namlp0212.accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Fetch API for get customer detail",
        description = "CFetch API for get customer detail"
)
@RestController
@RequestMapping(path="/customers/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CustomerController {
    @Autowired
    private ICustomersService iCustomersService;

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer, Account, Card and Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDetailDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        CustomerDetailDto customerDetailDto = iCustomersService.fetchCustomerDetail(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailDto);
    }
}
