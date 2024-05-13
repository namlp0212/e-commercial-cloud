package com.namlp0212.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "loans")
public class AccountContactInfoDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
