package com.paulovargas.api.config;

import com.paulovargas.api.entity.Client;
import com.paulovargas.api.entity.UserStatus;
import com.paulovargas.api.entity.UserType;
import com.paulovargas.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

@Configuration
public class DefaultUserConfig {

    @Bean
    public CommandLineRunner createDefaultUser(
            ClientRepository clientRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.default-user.name:Administrador}") String name,
            @Value("${app.default-user.email:admin@admin.com}") String email,
            @Value("${app.default-user.password:}") String password,
            @Value("${app.default-user.document:00000000000}") String document,
            @Value("${app.default-user.phone:00000000000}") String phone
    ) {
        return args -> {
            if (!StringUtils.hasText(password)) {
                return;
            }

            String normalizedEmail = email.trim().toLowerCase();

            if (clientRepository.existsByEmail(normalizedEmail)) {
                return;
            }

            Client admin = new Client();
            admin.setName(name.trim());
            admin.setEmail(normalizedEmail);
            admin.setPasswordHash(passwordEncoder.encode(password));
            admin.setDocument(document.replaceAll("\\D", ""));
            admin.setPhone(phone.replaceAll("\\D", ""));
            admin.setUserType(UserType.ADMIN);
            admin.setStatus(UserStatus.ACTIVE);
            admin.setSellerApproved(true);

            clientRepository.save(admin);
        };
    }
}
